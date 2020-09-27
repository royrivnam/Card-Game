package com.example.cardgame.entity;

import com.example.cardgame.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "player")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player extends AbstractEntity{

  @Column(name = "name")
  @JsonProperty(value = "name")
  private String playerName;

  @Column(name = "score")
  @JsonProperty(value = "score")
  private Integer playerGlobalScore;

  @Column(name = "level")
  @JsonProperty(value = "level")
  private Integer playerLevel;

  @Column(name = "icon")
  @JsonProperty(value = "icon")
  private String playerIcon;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  @JsonProperty(value = "status")
  private Status playerStatus;

}
