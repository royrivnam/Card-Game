package com.example.cardgame.dto;

import com.example.cardgame.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class PlayerDto {

  @JsonProperty(value = "id")
  private String playerId;

  @JsonProperty(value = "name")
  private String playerName;

  @JsonProperty(value = "score")
  private Integer playerGlobalScore;

  @JsonProperty(value="level")
  private Integer playerLevel;

  @JsonProperty(value="icon")
  private String playerIcon;

  @JsonProperty(value = "status")
  private Status playerStatus;

}
