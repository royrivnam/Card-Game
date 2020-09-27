package com.example.cardgame.repo;

import com.example.cardgame.entity.Gameplay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameplayRepository extends JpaRepository<Gameplay, Integer> {



}
