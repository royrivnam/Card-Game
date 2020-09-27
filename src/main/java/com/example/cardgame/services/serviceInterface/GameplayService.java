package com.example.cardgame.services.serviceInterface;

import com.example.cardgame.dto.CardDto;
import com.example.cardgame.dto.GameplayDto;
import com.example.cardgame.enums.Color;
import com.example.cardgame.enums.Suit;
import com.example.cardgame.exception.ResourceNotFoundException;

public interface GameplayService {

  GameplayDto getGameStartData(Integer gameId);

  CardDto getSingleSpecificCard(Integer gameId, Suit suit, Color color);

  GameplayDto gameMoveData(Integer gameId, Integer pickedCardId) throws ResourceNotFoundException;
}
