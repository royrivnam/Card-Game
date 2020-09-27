package com.example.cardgame.services.serviceImpl;

import com.example.cardgame.dto.PlayerDto;
import com.example.cardgame.entity.Player;
import com.example.cardgame.enums.Status;
import com.example.cardgame.exception.InvalidDataException;
import com.example.cardgame.exception.UserNotFoundException;
import com.example.cardgame.services.serviceInterface.PlayerRepoManagementService;
import com.example.cardgame.services.serviceInterface.PlayerService;
import com.example.cardgame.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

  private static Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);

  @Autowired
  PlayerRepoManagementService playerRepoManagementService;

  @Autowired
  Mapper mapper;

  @Override
  public PlayerDto createPlayer(PlayerDto playerDto) throws InvalidDataException {
    validatePlayer(playerDto);
    playerDto.setPlayerGlobalScore(0);
    playerDto.setPlayerLevel(1);
    playerDto.setPlayerStatus(Status.IDLE);
    Player player = playerRepoManagementService.createPlayer(playerDto);
    return mapper.entityToDto(player);
  }

  @Override
  public String deletePlayer(Integer id) throws UserNotFoundException {
    Player player = playerRepoManagementService.findPlayer(id);
    if(player==null)
    {
      logger.error("Player name can't be null");
      throw new UserNotFoundException("Player name can't be null");
    }
    playerRepoManagementService.deletePlayerById(player);
    return "OK";
  }

  private void validatePlayer(PlayerDto playerDto) throws InvalidDataException {
    if (playerDto.getPlayerName() == null) {
      logger.error("Player name can't be null");
      throw new InvalidDataException("Player name can't be null");
    }
    if (playerDto.getPlayerIcon() == null)
    {
      logger.error("Player icon can't be null");
      throw new InvalidDataException("Player icon can't be null");
    }
  }
}
