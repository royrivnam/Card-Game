package com.example.cardgame.controller;

import com.example.cardgame.constants.URIEndpoints;
import com.example.cardgame.dto.GameplayDto;
import com.example.cardgame.dto.PlayerDto;
import com.example.cardgame.dto.ServiceResponse;
import com.example.cardgame.exception.InvalidDataException;
import com.example.cardgame.exception.UserNotFoundException;
import com.example.cardgame.services.serviceInterface.PlayerService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping(URIEndpoints.CARDGAME + URIEndpoints.PLAYER)
@RestController
@Api(description = "Player APIs")
public class PlayerController {

  private static Logger logger = LoggerFactory.getLogger(PlayerController.class);

  @Autowired
  PlayerService playerService;

  @RequestMapping(value = "/create-player", method = RequestMethod.POST)
  public ServiceResponse<PlayerDto> getData(@RequestBody PlayerDto playerDto)
          throws InvalidDataException {
    return new ServiceResponse<PlayerDto>(playerService.createPlayer(playerDto), HttpStatus.CREATED);
  }

  @RequestMapping(value = "/delete-player/{id}", method = RequestMethod.POST)
  public ServiceResponse<String> getData(@PathVariable Integer id)
          throws UserNotFoundException {
    return new ServiceResponse<String>(playerService.deletePlayer(id), HttpStatus.OK);
  }

}
