package com.example.cardgame.enums;

public enum Status {
  MATCHING("matching"),
  IN_GAME("in_game"),
  IDLE("idle");

  private final String status;

  Status(String type) {
    this.status = type;
  }
}
