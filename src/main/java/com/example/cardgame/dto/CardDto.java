package com.example.cardgame.dto;

import com.example.cardgame.enums.Color;
import com.example.cardgame.enums.Suit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class CardDto {

  @JsonProperty(value = "id")
  private Integer cardId;

  @JsonProperty(value = "suit")
  private Suit suit;

  @JsonProperty(value = "color")
  private Color color;

  @JsonProperty(value = "value")
  private String value;

  @JsonProperty(value = "hierarchy")
  private Integer hierarchy;

}
