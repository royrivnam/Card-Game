package com.example.cardgame.services.serviceInterface;

import com.example.cardgame.dto.GameplayDto;
import com.example.cardgame.dto.PlayerDto;

public interface GameService {
  GameplayDto createGame(Integer playerId);
}
