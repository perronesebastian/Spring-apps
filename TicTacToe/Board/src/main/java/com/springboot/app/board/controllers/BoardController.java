package com.springboot.app.board.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.board.dto.BoardRequest;
import com.springboot.app.board.dto.BoardResponse;
import com.springboot.app.board.services.IBoardService;

@RestController
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private IBoardService boardService;
	
	@PostMapping
	public ResponseEntity<BoardResponse> create(@RequestBody BoardRequest boardRequest) {
		return new ResponseEntity<BoardResponse> (boardService.create(boardRequest), HttpStatus.CREATED);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<BoardResponse> get(@PathVariable("id") Integer id) {
		return new ResponseEntity<BoardResponse>(boardService.get(id), HttpStatus.CREATED);
	}
	
	
}
