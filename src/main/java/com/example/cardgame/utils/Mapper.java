package com.example.cardgame.utils;

import com.example.cardgame.dto.CardDto;
import com.example.cardgame.dto.GameplayDto;
import com.example.cardgame.dto.PlayerDto;
import com.example.cardgame.entity.Card;
import com.example.cardgame.entity.Gameplay;
import com.example.cardgame.entity.Player;
import com.example.cardgame.enums.GameStatus;
import com.example.cardgame.enums.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

  private static final ModelMapper modelMapper = new ModelMapper();

  public Player dtoToEntity(PlayerDto playerDto)
  {
    Player player =  modelMapper.map(playerDto, Player.class);
    return player;
  }

  public PlayerDto entityToDto(Player player) {
    PlayerDto playerDto = modelMapper.map(player, PlayerDto.class);
    return playerDto;
  }

  public CardDto entityToDto(Card card)
  {
    return modelMapper.map(card, CardDto.class);
  }

  public GameplayDto gameplayDtoFromEntity(Gameplay gameplay, List<Card> cardList, Player player1, Player player2) {
    GameplayDto gameplayDto = new GameplayDto();
    gameplayDto.setUniqueGameId(gameplay.getId());
    gameplayDto.setPlayer1(entityToDto(player1));
    gameplayDto.setPlayer2(entityToDto(player2));
    gameplayDto.setCardList(cardList.stream().map(card -> entityToDto(card)).collect(Collectors.toList()));
    gameplayDto.setCardRemainingList(cardList.stream().map(card -> entityToDto(card)).collect(Collectors.toList()));
    gameplayDto.setThisTurn(gameplay.getThisTurn());
    gameplayDto.setStatus(gameplay.getStatus());
    gameplayDto.setTimer(gameplay.getTimer());
    return gameplayDto;
  }
}
