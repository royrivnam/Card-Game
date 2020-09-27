package com.example.cardgame.repo;

import com.example.cardgame.entity.Player;
import com.example.cardgame.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

  List<Player> findByPlayerStatus(Status status);

}
