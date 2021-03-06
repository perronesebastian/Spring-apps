package com.springboot.app.player.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.player.entities.PlayerEntity;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Integer> {
}
