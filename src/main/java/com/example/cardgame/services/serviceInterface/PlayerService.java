package com.example.cardgame.services.serviceInterface;

import com.example.cardgame.dto.PlayerDto;
import com.example.cardgame.exception.InvalidDataException;
import com.example.cardgame.exception.UserNotFoundException;

public interface PlayerService {

  PlayerDto createPlayer(PlayerDto playerDto) throws InvalidDataException;

  String deletePlayer(Integer id) throws UserNotFoundException;
}
