package com.example.cardgame.entity;

import com.example.cardgame.dto.PlayerMoveDto;
import com.example.cardgame.enums.GameStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "gameplay")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Gameplay extends AbstractEntity{

  @Column(name = "player_one")
  @JsonProperty(value = "player_one")
  private Integer playerId1;

  @Column(name = "player_two")
  @JsonProperty(value = "player_two")
  private Integer playerId2;

  @Type(type = "jsonb")
  @Column(name = "cards")
  @JsonProperty("cards")
  private List<Integer> cardList;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  @JsonProperty("status")
  private GameStatus status;

  @Column(name = "winner")
  @JsonProperty("winner")
  private Integer winner;

  @Type(type = "jsonb")
  @Column(name = "card_remaining")
  @JsonProperty("card_remaining")
  private List<Integer> cardRemainingList;

  @Type(type = "jsonb")
  @JsonProperty("player_one_moves")
  @Column(name = "player_one_moves")
  private List<PlayerMove> playerOneMove;

  @Type(type = "jsonb")
  @JsonProperty("player_two_moves")
  @Column(name = "player_two_moves")
  private List<PlayerMove> playerTwoMove;

  @JsonProperty("turn")
  private Integer thisTurn;

  @JsonProperty("timer")
  private Long timer;

}
