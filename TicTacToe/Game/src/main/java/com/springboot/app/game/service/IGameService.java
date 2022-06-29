package com.springboot.app.game.service;

import com.springboot.app.game.dto.GameRequest;
import com.springboot.app.game.dto.GameResponse;

public interface IGameService {

	GameResponse create(GameRequest gameRequest);

	GameResponse get(Integer id);

}
