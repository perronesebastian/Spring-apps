package com.springboot.app.player.services;

import com.springboot.app.player.dtos.CreatePlayerResponse;
import com.springboot.app.player.dtos.PlayerRequest;
import com.springboot.app.player.dtos.GetPlayerResponse;

public interface IPlayerService {

	CreatePlayerResponse create(PlayerRequest playerRequest);
	
	GetPlayerResponse get(Integer id);

}
