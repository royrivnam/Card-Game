package com.example.cardgame.services.serviceImpl;

import com.example.cardgame.entity.Card;
import com.example.cardgame.repo.CardRepository;
import com.example.cardgame.services.serviceInterface.CardRepoManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardRepoManagementServiceImpl implements CardRepoManagementService {

  private static Logger logger = LoggerFactory.getLogger(CardRepoManagementServiceImpl.class);

  @Autowired
  CardRepository cardRepository;

  @Override
  public List<Card> findAll() {
    return cardRepository.findAll();
  }

  @Override
  public Card createCard(Card card) {
    return cardRepository.save(card);
  }
}
