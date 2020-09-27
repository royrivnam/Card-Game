package com.example.cardgame.services.serviceImpl;

import com.example.cardgame.dto.GameplayDto;
import com.example.cardgame.entity.Card;
import com.example.cardgame.entity.Gameplay;
import com.example.cardgame.entity.Player;
import com.example.cardgame.enums.GameStatus;
import com.example.cardgame.enums.Status;
import com.example.cardgame.services.serviceInterface.CardRepoManagementService;
import com.example.cardgame.services.serviceInterface.GameService;
import com.example.cardgame.services.serviceInterface.GameplayRepoManagementService;
import com.example.cardgame.services.serviceInterface.PlayerRepoManagementService;
import com.example.cardgame.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
  private static Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

  @Autowired
  PlayerRepoManagementService playerRepoManagementService;

  @Autowired
  Mapper mapper;

  @Autowired
  CardRepoManagementService cardRepoManagementService;

  @Autowired
  GameplayRepoManagementService gameplayRepoManagementService;

  @Value("${score.point.win}")
  private Integer winPoint;

  @Value("${score.point.loose}")
  private Integer loosePoint;

  @Value("${score.point.draw}")
  private Integer drawPoint;

  @Transactional
  @Override
  public GameplayDto createGame(Integer playerId) {
    Player player1 = playerRepoManagementService.findPlayer(playerId);
    Player player2 = null;
    List<Player> playerList = playerRepoManagementService.findPlayerByStatus();
    if(Objects.isNull(playerList)) {
      player1.setPlayerStatus(Status.MATCHING);
      playerRepoManagementService.updatePlayerStatus(player1);
    }
    else {
      int pos = (int) (Math.random() * (playerList.size() - 1));
      player2 = playerList.get(pos);
      player2.setPlayerStatus(Status.IN_GAME);
      player1.setPlayerStatus(Status.IN_GAME);
      playerRepoManagementService.updatePlayerStatus(player1);
      playerRepoManagementService.updatePlayerStatus(player2);
    }
    return createGameData(player1,player2);

  }

  private GameplayDto createGameData(Player player1, Player player2) {
    Gameplay gameplay = new Gameplay();
    List<Card> cardList = cardRepoManagementService.findAll();
    gameplay.setPlayerId1(player1.getId());
    gameplay.setPlayerId2(player2.getId());
    gameplay.setCardList(cardList.stream().map(card -> card.getId()).collect(Collectors.toList()));
    gameplay.setCardRemainingList(cardList.stream().map(card -> card.getId()).collect(Collectors.toList()));
    gameplay.setStatus(GameStatus.IN_PROGRESS);
    if((Math.random()*2) > 1)
    {
      gameplay.setThisTurn(player2.getId());
    }
    else
    {
      gameplay.setThisTurn(player1.getId());
    }
    gameplay.setTimer(30000L);
    gameplay = gameplayRepoManagementService.createGame(gameplay);
    return mapper.gameplayDtoFromEntity(gameplay, cardList, player1, player2);
  }

  @Transactional
  public void endGame(Integer gameId, Integer playerLeftId)
  {
    Gameplay gameplay = gameplayRepoManagementService.getGameData(gameId);
    Player player1 = playerRepoManagementService.findPlayer(gameplay.getPlayerId1());
    Player player2 = playerRepoManagementService.findPlayer(gameplay.getPlayerId2());
    gameplay.setStatus(GameStatus.ENDED);
    gameplayRepoManagementService.updateGame(gameplay);
    if(gameplay.getWinner()==null && CollectionUtils.isEmpty(gameplay.getCardRemainingList()))
    {
      player1.setPlayerGlobalScore(player1.getPlayerGlobalScore()+drawPoint);
      player2.setPlayerGlobalScore(player2.getPlayerGlobalScore()+drawPoint);
    }
    else if(gameplay.getWinner()==null && playerLeftId!=null)
    {
      if(player1.getId()==playerLeftId)
      {
        player1.setPlayerGlobalScore(player1.getPlayerGlobalScore()+loosePoint);
        player2.setPlayerGlobalScore(player2.getPlayerGlobalScore()+winPoint);
      }
      else
      {
        player1.setPlayerGlobalScore(player1.getPlayerGlobalScore()+winPoint);
        player2.setPlayerGlobalScore(player2.getPlayerGlobalScore()+loosePoint);
      }
    }
    else
    {
      if(gameplay.getWinner()==player1.getId()) {
        player1.setPlayerGlobalScore(player1.getPlayerGlobalScore()+winPoint);
        player2.setPlayerGlobalScore(player2.getPlayerGlobalScore()+loosePoint);
      }
      else {
        player1.setPlayerGlobalScore(player1.getPlayerGlobalScore()+loosePoint);
        player2.setPlayerGlobalScore(player2.getPlayerGlobalScore()+winPoint);
      }
    }
    player1.setPlayerStatus(Status.IDLE);
    player1.setPlayerLevel(player1.getPlayerGlobalScore()/1000);
    player2.setPlayerStatus(Status.IDLE);
    player2.setPlayerLevel(player2.getPlayerGlobalScore()/1000);
    playerRepoManagementService.updatePlayerStatus(player1);
    playerRepoManagementService.updatePlayerStatus(player2);
  }
}
