package com.example.cardgame.services.serviceInterface;

import com.example.cardgame.dto.PlayerDto;
import com.example.cardgame.entity.Player;

import java.util.List;

public interface PlayerRepoManagementService {
  Player createPlayer(PlayerDto playerDto);

  Player findPlayer(Integer id);

  void deletePlayerById(Player player);

  List<Player> findPlayerByStatus();

  void updatePlayerStatus(Player player);
}
