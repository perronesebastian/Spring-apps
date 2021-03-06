package com.springboot.app.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.game.entities.GameEntity;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Integer> {
}
