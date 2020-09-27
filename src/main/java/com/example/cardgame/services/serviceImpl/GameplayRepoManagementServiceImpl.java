package com.example.cardgame.services.serviceImpl;

import com.example.cardgame.entity.Gameplay;
import com.example.cardgame.repo.GameplayRepository;
import com.example.cardgame.services.serviceInterface.GameplayRepoManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameplayRepoManagementServiceImpl implements GameplayRepoManagementService {

  private static Logger logger = LoggerFactory.getLogger(GameplayRepoManagementServiceImpl.class);

  @Autowired
  GameplayRepository gameplayRepository;

  @Override
  public Gameplay createGame(Gameplay gameplay) {
    return gameplayRepository.save(gameplay);
  }

  @Override
  public Gameplay getGameData(Integer uniqueGameId) {
    return gameplayRepository.findById(uniqueGameId).orElse(null);
  }

  @Override
  public Gameplay updateGame(Gameplay gameplay) {
    return gameplayRepository.save(gameplay);
  }
}
