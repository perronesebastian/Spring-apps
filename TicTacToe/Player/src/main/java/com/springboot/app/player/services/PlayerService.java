package com.springboot.app.player.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.springboot.app.player.dtos.CreatePlayerResponse;
import com.springboot.app.player.dtos.PlayerRequest;
import com.springboot.app.player.dtos.GetPlayerResponse;
import com.springboot.app.player.entities.PlayerEntity;
import com.springboot.app.player.repository.PlayerRepository;
import com.springboot.app.player.restClient.CoordinateRestClient;

@Service
public class PlayerService implements IPlayerService {
	
	@Autowired
	private CoordinateRestClient coordinateClientRest;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Override
	public CreatePlayerResponse create(PlayerRequest playerRequest) {
		PlayerEntity playerEntity = new PlayerEntity();
		playerRepository.save(this.toEntity(playerRequest, playerEntity));
		return this.mapperToCreate(playerEntity);
	}
	
	@Override
	public GetPlayerResponse get(Integer id) {
		PlayerEntity playerEntity = playerRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Player with id %s does not exist", id)));
		return this.mapperToGet(playerEntity);
	}
	
	private GetPlayerResponse mapperToGet(PlayerEntity playerEntity) {
		GetPlayerResponse playerResponse = new GetPlayerResponse();
		playerResponse.setId(playerEntity.getId());
		playerResponse.setUsername(playerEntity.getUsername());
		playerResponse.setCoordinates(coordinateClientRest.getCoordinates(playerEntity.getId()));
		return playerResponse;
	}
	
	private CreatePlayerResponse mapperToCreate(PlayerEntity playerEntity) {
		CreatePlayerResponse createPlayerResponse = new CreatePlayerResponse();
		createPlayerResponse.setId(playerEntity.getId());
		createPlayerResponse.setUsername(playerEntity.getUsername());
		return createPlayerResponse;
	}
	
	private PlayerEntity toEntity(PlayerRequest playerDto, PlayerEntity playerEntity) {
		playerEntity.setUsername(playerDto.getUsername());
		playerEntity.setPassword(playerDto.getPassword());
		return playerEntity;
	}
}