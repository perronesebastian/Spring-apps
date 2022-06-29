package com.springboot.app.player.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.player.dtos.CreatePlayerResponse;
import com.springboot.app.player.dtos.PlayerRequest;
import com.springboot.app.player.dtos.GetPlayerResponse;
import com.springboot.app.player.services.IPlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {
	
	@Autowired
	private IPlayerService playerService;
	
	@PostMapping
	public ResponseEntity<CreatePlayerResponse> create(@RequestBody PlayerRequest playerRequest) {
		return new ResponseEntity<CreatePlayerResponse>(playerService.create(playerRequest), HttpStatus.CREATED);
	}
	
	@GetMapping("id/{id}")
	public ResponseEntity<GetPlayerResponse> get(@PathVariable("id") Integer id) {
		return new ResponseEntity<GetPlayerResponse>(playerService.get(id), HttpStatus.OK);
	}
}
