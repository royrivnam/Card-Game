package com.example.cardgame.controller;

import com.example.cardgame.constants.URIEndpoints;
import com.example.cardgame.dto.CardDto;
import com.example.cardgame.entity.Card;
import com.example.cardgame.enums.Color;
import com.example.cardgame.enums.Suit;
import com.example.cardgame.services.serviceInterface.CardRepoManagementService;
import com.example.cardgame.services.serviceInterface.GameplayService;
import com.example.cardgame.utils.Mapper;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RequestMapping(URIEndpoints.CARDGAME)
@RestController
@Api(description = "Test APIs")
public class TestController {

  private static Logger logger = LoggerFactory.getLogger(TestController.class);

  @Autowired
  CardRepoManagementService cardRepoManagementService;

  @Autowired
  GameplayService gameplayService;

  @Autowired
  Mapper mapper;

  @RequestMapping(value = "/ping", method = RequestMethod.GET)
  public String getData() {
    logger.info("Ping received");
    return "pong";
  }

  @RequestMapping(value = "/get-all-cards", method = RequestMethod.GET)
  public List<CardDto> getCards() {
    logger.info("Card fetch request received");
    List<Card> cardList = cardRepoManagementService.findAll();
    return cardList.stream().map(card -> mapper.entityToDto(card)).collect(Collectors.toList());
  }

  @RequestMapping(value = "/create-all-cards", method = RequestMethod.GET)
  public List<CardDto> createCards() {
    logger.info("Create all card request received");
    List<CardDto> cardDtoList = new ArrayList<>();
    for (int i = 2; i <= 10; i++) {
      Card card = new Card();
      card.setColor(Color.RED);
      card.setSuit(Suit.DIAMONDS);
      card.setValue(String.valueOf(i));
      card.setHierarchy(i);
      card = cardRepoManagementService.createCard(card);
      cardDtoList.add(mapper.entityToDto(card));
    }
    for (int i = 2; i <= 10; i++) {
      Card card = new Card();
      card.setColor(Color.RED);
      card.setSuit(Suit.HEARTS);
      card.setValue(String.valueOf(i));
      card.setHierarchy(i);
      card = cardRepoManagementService.createCard(card);
      cardDtoList.add(mapper.entityToDto(card));
    }
    for (int i = 2; i <= 10; i++) {
      Card card = new Card();
      card.setColor(Color.BLACK);
      card.setSuit(Suit.CLUBS);
      card.setValue(String.valueOf(i));
      card.setHierarchy(i);
      card = cardRepoManagementService.createCard(card);
      cardDtoList.add(mapper.entityToDto(card));
    }
    for (int i = 2; i <= 10; i++) {
      Card card = new Card();
      card.setColor(Color.BLACK);
      card.setSuit(Suit.SPADES);
      card.setValue(String.valueOf(i));
      card.setHierarchy(i);
      card = cardRepoManagementService.createCard(card);
      cardDtoList.add(mapper.entityToDto(card));
    }
    for ( int i=11; i<15; i++)
    {
      String value = null;
      Integer hierarchy = i;
      if(i==11)
      {
        value = "A";
        hierarchy = 1;
      }
      else if(i==12)
        value = "J";
      else if(i==13)
        value = "Q";
      else if(i==14)
        value = "K";
      Card card = new Card();
      card.setColor(Color.RED);
      card.setSuit(Suit.DIAMONDS);
      card.setValue(value);
      card.setHierarchy(hierarchy);
      card = cardRepoManagementService.createCard(card);
      cardDtoList.add(mapper.entityToDto(card));
      card = new Card();
      card.setColor(Color.RED);
      card.setSuit(Suit.HEARTS);
      card.setValue(value);
      card.setHierarchy(hierarchy);
      card = cardRepoManagementService.createCard(card);
      cardDtoList.add(mapper.entityToDto(card));
      card = new Card();
      card.setColor(Color.BLACK);
      card.setSuit(Suit.CLUBS);
      card.setValue(value);
      card.setHierarchy(hierarchy);
      card = cardRepoManagementService.createCard(card);
      cardDtoList.add(mapper.entityToDto(card));
      card = new Card();
      card.setColor(Color.BLACK);
      card.setSuit(Suit.SPADES);
      card.setValue(value);
      card.setHierarchy(hierarchy);
      card = cardRepoManagementService.createCard(card);
      cardDtoList.add(mapper.entityToDto(card));
    }
    return cardDtoList;
  }

}
