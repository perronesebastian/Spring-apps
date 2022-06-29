package com.springboot.app.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.springboot.app.game.dto.GameRequest;
import com.springboot.app.game.dto.GameResponse;
import com.springboot.app.game.entities.GameEntity;
import com.springboot.app.game.repository.GameRepository;
import com.springboot.app.game.restClient.BoardRestClient;
import com.springboot.app.game.restClient.PlayerRestClient;


@Service
public class GameService implements IGameService {
		
//	@Autowired
//	public PlayerClientRest playerClientRest;
//	
//	@Autowired
//	public BoardClientRest boardClientRest;
	
	@Autowired
	public PlayerRestClient playerRestClient;
	
	@Autowired
	public BoardRestClient boardRestClient;
	
	@Autowired
	private GameRepository gameRepository;
	
	@Override
	public GameResponse create(GameRequest gameRequest) {
		GameEntity gameEntity = new GameEntity();
		gameRepository.save(this.mapperToEntity(gameRequest, gameEntity));
		return this.mapperToResponse(gameEntity);
	}
	
	@Override
	public GameResponse get(Integer id) {
		GameEntity gameEntity = gameRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Game id %s does not exist", id)));
		return this.mapperToResponse(gameEntity);
	}
	
	private GameResponse mapperToResponse(GameEntity gameEntity) {
		GameResponse gameResponse = new GameResponse();
		gameResponse.setId(gameEntity.getId());
		gameResponse.setBoard(boardRestClient.get(gameEntity.getBoard_id()));
		gameResponse.setPlayer_1(playerRestClient.get(gameEntity.getPlayer_id_1()));
		gameResponse.setPlayer_2(playerRestClient.get(gameEntity.getPlayer_id_2()));
		return gameResponse;
	}
	
	private GameEntity mapperToEntity(GameRequest gameRequest, GameEntity gameEntity) {
		gameEntity.setBoard_id(gameRequest.getBoard_id());
		gameEntity.setPlayer_id_1(gameRequest.getPlayer_id_1());
		gameEntity.setPlayer_id_2(gameRequest.getPlayer_id_2());
		return gameEntity;
	}
}
