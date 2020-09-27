package com.example.cardgame.entity;

import com.example.cardgame.enums.Color;
import com.example.cardgame.enums.Suit;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card extends AbstractEntity{

  @Enumerated(EnumType.STRING)
  @Column(name = "suit")
  @JsonProperty(value = "suit")
  private Suit suit;

  @Enumerated(EnumType.STRING)
  @Column(name = "color")
  @JsonProperty(value = "color")
  private Color color;

  @Column(name = "value")
  @JsonProperty(value = "value")
  private String value;

  @JsonProperty(value = "hierarchy")
  @Column(name = "hierarchy")
  private Integer hierarchy;
}
