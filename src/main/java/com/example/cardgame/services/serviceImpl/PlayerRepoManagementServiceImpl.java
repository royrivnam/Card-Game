package com.example.cardgame.services.serviceImpl;

import com.example.cardgame.dto.PlayerDto;
import com.example.cardgame.entity.Player;
import com.example.cardgame.enums.Status;
import com.example.cardgame.repo.PlayerRepository;
import com.example.cardgame.services.serviceInterface.PlayerRepoManagementService;
import com.example.cardgame.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerRepoManagementServiceImpl implements PlayerRepoManagementService {

  private static Logger logger = LoggerFactory.getLogger(PlayerRepoManagementServiceImpl.class);

  @Autowired
  Mapper mapper;

  @Autowired
  PlayerRepository playerRepository;

  @Override
  public Player createPlayer(PlayerDto playerDto) {
    Player player = mapper.dtoToEntity(playerDto);
    playerRepository.save(player);
    return player;
  }

  @Override
  public Player findPlayer(Integer id) {
    return playerRepository.findById(id).orElse(null);
  }

  @Override
  public void deletePlayerById(Player player) {
    playerRepository.delete(player);
  }

  @Override
  public List<Player> findPlayerByStatus() {
    return playerRepository.findByPlayerStatus(Status.MATCHING);
  }

  @Override
  public void updatePlayerStatus(Player player) {
    playerRepository.save(player);
  }
}
