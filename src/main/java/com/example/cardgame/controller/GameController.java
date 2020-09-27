package com.example.cardgame.controller;

import com.example.cardgame.constants.URIEndpoints;
import com.example.cardgame.dto.GameplayDto;
import com.example.cardgame.dto.PlayerDto;
import com.example.cardgame.dto.ServiceResponse;
import com.example.cardgame.exception.InvalidDataException;
import com.example.cardgame.services.serviceImpl.PlayerServiceImpl;
import com.example.cardgame.services.serviceInterface.GameService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping(URIEndpoints.CARDGAME + URIEndpoints.GAME)
@RestController
@Api(description = "Game APIs")
public class GameController {

  private static Logger logger = LoggerFactory.getLogger(GameController.class);

  @Autowired
  GameService gameService;

  @RequestMapping(value = "/create-game", method = RequestMethod.POST)
  public ServiceResponse<GameplayDto> getData(@RequestParam Integer playerId)
          throws InvalidDataException {
    return new ServiceResponse<GameplayDto>(gameService.createGame(playerId), HttpStatus.CREATED);
  }

}
