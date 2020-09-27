package com.example.cardgame.services.serviceImpl;

import com.example.cardgame.dto.CardDto;
import com.example.cardgame.dto.PlayerMoveDto;
import com.example.cardgame.entity.Player;
import com.example.cardgame.enums.Color;
import com.example.cardgame.enums.GameStatus;
import com.example.cardgame.enums.Suit;
import org.springframework.util.CollectionUtils;
import com.example.cardgame.dto.GameplayDto;
import com.example.cardgame.entity.Card;
import com.example.cardgame.entity.Gameplay;
import com.example.cardgame.entity.PlayerMove;
import com.example.cardgame.exception.ResourceNotFoundException;
import com.example.cardgame.services.serviceInterface.CardRepoManagementService;
import com.example.cardgame.services.serviceInterface.GameplayRepoManagementService;
import com.example.cardgame.services.serviceInterface.GameplayService;
import com.example.cardgame.services.serviceInterface.PlayerRepoManagementService;
import com.example.cardgame.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameplayServiceImpl implements GameplayService {
  private static Logger logger = LoggerFactory.getLogger(GameplayServiceImpl.class);

  @Autowired
  PlayerRepoManagementService playerRepoManagementService;

  @Autowired
  Mapper mapper;

  @Autowired
  CardRepoManagementService cardRepoManagementService;

  @Autowired
  GameplayRepoManagementService gameplayRepoManagementService;

  @Override
  public GameplayDto getGameStartData(Integer gameId) {


    return null;
  }

  @Transactional
  @Override
  public CardDto getSingleSpecificCard(Integer gameId, Suit suit, Color color) {
    List<Card> cardList = cardRepoManagementService.findAll();
    Map<Integer,Card> cardMap = cardList.stream().collect(Collectors.toMap(card -> card.getId(),card -> card));
    Gameplay gameplay = gameplayRepoManagementService.getGameData(gameId);
    return getCardOnSuitOrColor(suit,color,gameplay.getCardRemainingList(),cardMap);
  }

  @Transactional
  private GameplayDto gameMoveData(Integer gameId, Integer pickedCardId) throws ResourceNotFoundException {
    List<Card> cardList = cardRepoManagementService.findAll();
    Map<Integer,Card> cardMap = cardList.stream().collect(Collectors.toMap(card -> card.getId(),card -> card));
    Gameplay gameplay = gameplayRepoManagementService.getGameData(gameId);
    if(Objects.isNull(gameplay))
    {
      logger.error("Game data not found for game id "+ gameId);
      throw new ResourceNotFoundException("Game data not found for game id "+ gameId);
    }
    gameplay.setCardRemainingList(gameplay.
                                    getCardRemainingList().
                                    stream().
                                    filter(card -> card!=pickedCardId).collect(Collectors.toList()));
    Integer turn = gameplay.getThisTurn();
    if(turn==gameplay.getPlayerId1())
    {
      gameplay.getPlayerOneMove().add(new PlayerMove(new Date().getTime(),pickedCardId));
      if(checkCardSequence(gameplay.getPlayerOneMove(),cardMap))
      {
        gameplay.setWinner(gameplay.getPlayerId1());
        gameplay.setStatus(GameStatus.ENDED);
      }
      gameplay.setThisTurn(gameplay.getPlayerId2());
    }
    else {
      gameplay.getPlayerTwoMove().add(new PlayerMove(new Date().getTime(),pickedCardId));
      if(checkCardSequence(gameplay.getPlayerTwoMove(),cardMap))
      {
        gameplay.setWinner(gameplay.getPlayerId1());
        gameplay.setStatus(GameStatus.ENDED);
      }
      gameplay.setThisTurn(gameplay.getPlayerId1());
    }
    gameplayRepoManagementService.updateGame(gameplay);
    Player player1 = playerRepoManagementService.findPlayer(gameplay.getPlayerId1());
    Player player2 = playerRepoManagementService.findPlayer(gameplay.getPlayerId2());
    GameplayDto gameplayDto = mapper.gameplayDtoFromEntity(gameplay,cardList,player1,player2);
    gameplayDto.setCardRemainingList(gameplay.getCardRemainingList().stream().map(card -> mapper.entityToDto(cardMap.get(card))).collect(Collectors.toList()));
    if(turn==gameplay.getPlayerId2()) {
      gameplayDto.setPlayerOneMove(gameplay.getPlayerOneMove().stream().map(playerMove -> new PlayerMoveDto(playerMove.getTimestamp(), mapper.entityToDto(cardMap.get(playerMove.getCardId())))).collect(Collectors.toList()));
    }
    else {
      gameplayDto.setPlayerTwoMove(gameplay.getPlayerTwoMove().stream().map(playerMove -> new PlayerMoveDto(playerMove.getTimestamp(), mapper.entityToDto(cardMap.get(playerMove.getCardId())))).collect(Collectors.toList()));
    }
    gameplayDto.setWinner(gameplay.getWinner());
    return gameplayDto;
  }

  private boolean checkCardSequence(List<PlayerMove> playerOneMove, Map<Integer,Card> cardMap) {
    List<Card> cardList = playerOneMove.stream().map(playerMove -> cardMap.get(playerMove.getCardId())).collect(Collectors.toList());
    int count = 0;
    if(!CollectionUtils.isEmpty(cardList) && cardList.size()>3)
    for(int i=1; i<cardList.size(); i++)
    {
      if(cardList.get(i).getHierarchy()> cardList.get(i-1).getHierarchy())
      {
        count++;
      }
      else
      {
        count=0;
      }
      if(count==4)
        return true;
    }
    return false;
  }

  private CardDto getCardOnSuitOrColor(Suit suit, Color color, List<Integer> remainingCardList, Map<Integer,Card> cardMap)
  {
    List<Card> suitSpecificCard = null;
    List<Card> colorSpecificCard = null;
    if(suit!=null)
      suitSpecificCard = remainingCardList.stream().filter(cardId -> suit.equals(cardMap.get(cardId).getSuit())).map(cardId -> cardMap.get(cardId)).collect(Collectors.toList());
    if(color!=null)
      colorSpecificCard = remainingCardList.stream().filter(cardId -> color.equals(cardMap.get(cardId).getColor())).map(cardId -> cardMap.get(cardId)).collect(Collectors.toList());

    if(!CollectionUtils.isEmpty(suitSpecificCard))
      return mapper.entityToDto(suitSpecificCard.get((int) (Math.random() * (suitSpecificCard.size() - 1))));
    if(!CollectionUtils.isEmpty(colorSpecificCard))
      return mapper.entityToDto(colorSpecificCard.get((int) (Math.random() * (colorSpecificCard.size() - 1))));
    return mapper.entityToDto(cardMap.get(remainingCardList.get((int) (Math.random() * (colorSpecificCard.size() - 1)))));
  }



}
