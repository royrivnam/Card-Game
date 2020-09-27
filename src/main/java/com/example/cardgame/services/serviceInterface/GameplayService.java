package com.example.cardgame.services.serviceInterface;

import com.example.cardgame.dto.CardDto;
import com.example.cardgame.dto.GameplayDto;
import com.example.cardgame.enums.Color;
import com.example.cardgame.enums.Suit;

public interface GameplayService {

  GameplayDto getGameStartData(Integer gameId);

  CardDto getSingleSpecificCard(Integer gameId, Suit suit, Color color);
}
