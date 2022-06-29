package com.springboot.app.game.clients;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.app.game.dto.PlayerDto;


//@FeignClient(name = "tictactoe-player", url = "localhost:8002")
public interface PlayerClientRest {
		
	@GetMapping("/player/id/{id}")
	PlayerDto getPlayerById(@PathVariable("id") Integer id);
}
