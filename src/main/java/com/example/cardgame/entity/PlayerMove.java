package com.example.cardgame.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerMove extends AbstractEntity{

  private Long timestamp;

  private Integer cardId;

}
