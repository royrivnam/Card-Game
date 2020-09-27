package com.example.cardgame.controller;

import com.example.cardgame.constants.URIEndpoints;
import com.example.cardgame.dto.CardDto;
import com.example.cardgame.dto.GameplayDto;
import com.example.cardgame.dto.ServiceResponse;
import com.example.cardgame.enums.Color;
import com.example.cardgame.enums.Suit;
import com.example.cardgame.exception.InvalidDataException;
import com.example.cardgame.services.serviceInterface.GameplayService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping(URIEndpoints.CARDGAME + URIEndpoints.GAMEPLAY)
@RestController
@Api(description = "Gameplay APIs")
public class GameplayController {

  private static Logger logger = LoggerFactory.getLogger(GameplayController.class);

  @Autowired
  GameplayService gameplayService;

  @RequestMapping(value = "/get-data", method = RequestMethod.GET)
  public ServiceResponse<GameplayDto> getData(@RequestParam(value = "game_id") Integer gameId)
          throws InvalidDataException {
    return new ServiceResponse<GameplayDto>(gameplayService.getGameStartData(gameId), HttpStatus.CREATED);
  }

  @RequestMapping(value = "/update-data", method = RequestMethod.GET)
  public ServiceResponse<GameplayDto> updateData(@RequestParam(value = "game_id") Integer gameId)
          throws InvalidDataException {
    return new ServiceResponse<GameplayDto>(gameplayService.getGameStartData(gameId), HttpStatus.CREATED);
  }

  @RequestMapping(value = "/get-specific-card-type", method = RequestMethod.GET)
  public ServiceResponse<CardDto> getSpecificCardType(@RequestParam(value = "game_id") Integer gameId, @RequestParam(value = "suit", required = false) Suit suit, @RequestParam(value = "suit", required = false) Color color)
          throws InvalidDataException {
    return new ServiceResponse<CardDto>(gameplayService.getSingleSpecificCard(gameId, suit, color), HttpStatus.CREATED);
  }

}
