package com.springboot.app.game.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.game.dto.GameRequest;
import com.springboot.app.game.dto.GameResponse;
import com.springboot.app.game.service.IGameService;

@RestController
@RequestMapping("/game")
public class GameController {
	
	@Autowired
	private IGameService gameService;
	
	@PostMapping("/create")
	public ResponseEntity<GameResponse> create(@RequestBody GameRequest gameRequest) {
		return new ResponseEntity<GameResponse>(gameService.create(gameRequest), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GameResponse> get(@PathVariable("id") Integer id) {
		return new ResponseEntity<GameResponse>(gameService.get(id), HttpStatus.OK);
	}

}
