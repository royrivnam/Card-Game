package com.example.cardgame.services.serviceInterface;

import com.example.cardgame.entity.Card;

import java.util.List;

public interface CardRepoManagementService {
  List<Card> findAll();

  Card createCard(Card card);
}
