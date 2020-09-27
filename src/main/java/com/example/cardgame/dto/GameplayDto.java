package com.example.cardgame.dto;

import com.example.cardgame.enums.GameStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class GameplayDto {

  @JsonProperty(value = "id")
  private Integer uniqueGameId;

  @JsonProperty(value = "player_one")
  private PlayerDto player1;

  @JsonProperty(value = "player_two")
  private PlayerDto player2;

  @JsonProperty(value = "cards")
  List<CardDto> cardList;

  @JsonProperty(value = "status")
  private GameStatus status;

  @JsonProperty(value = "winner")
  private Integer winner;

  @JsonProperty(value = "card_remaining")
  List<CardDto> cardRemainingList;

  @JsonProperty(value = "player_one_moves")
  private List<PlayerMoveDto> playerOneMove;

  @JsonProperty(value = "player_two_moves")
  private List<PlayerMoveDto> playerTwoMove;

  @JsonProperty(value = "turn")
  private Integer thisTurn;

  @JsonProperty(value = "timer")
  private Long timer;

}
