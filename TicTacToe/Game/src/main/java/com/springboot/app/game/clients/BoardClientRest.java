package com.springboot.app.game.clients;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.app.game.dto.BoardDto;

//@FeignClient(name = "tictactoe-board", url = "localhost:8003")
public interface BoardClientRest {
	
	@GetMapping("/board/id/{id}")
	BoardDto getBoardById(@PathVariable("id") Integer id);
}
