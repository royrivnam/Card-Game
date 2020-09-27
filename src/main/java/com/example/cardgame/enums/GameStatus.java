package com.example.cardgame.enums;

public enum  GameStatus {
  IN_PROGRESS("in_progress"),
  ENDED("ended");

  private final String gameStatus;

  GameStatus(String type) {
    this.gameStatus = type;
  }
}
