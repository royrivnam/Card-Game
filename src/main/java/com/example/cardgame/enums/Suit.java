package com.example.cardgame.enums;

public enum Suit {

  SPADES("spades"),
  CLUBS("clubs"),
  DIAMONDS("diamonds"),
  HEARTS("hearts");

  private final String suit;

  Suit(String type) {
    this.suit = type;
  }
}
