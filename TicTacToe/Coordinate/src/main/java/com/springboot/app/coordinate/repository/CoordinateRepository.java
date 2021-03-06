package com.springboot.app.coordinate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.app.coordinate.entities.CoordinateEntity;

@Repository
public interface CoordinateRepository extends JpaRepository<CoordinateEntity, Integer> {
	
	@Query("SELECT c FROM CoordinateEntity c WHERE c.player_id=?1")
	List<CoordinateEntity> findByPlayerId(Integer playerId);
}
