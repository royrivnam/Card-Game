package com.example.cardgame.services.serviceInterface;

import com.example.cardgame.entity.Gameplay;

public interface GameplayRepoManagementService {
  Gameplay createGame(Gameplay gameplay);

  Gameplay getGameData(Integer uniqueGameId);

  Gameplay updateGame(Gameplay gameplay);
}
