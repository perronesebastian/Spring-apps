package com.springboot.app.game.restClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import com.springboot.app.game.dto.PlayerDto;

@Service
public class PlayerRestClient {
	
	@Autowired
	@Qualifier("PlayerWebClient")
	private WebClient webClient;
	
	public PlayerDto get(Integer id) {
		try {
			PlayerDto player = webClient.method(HttpMethod.GET)
					.uri("/player/id/" + id)
					.retrieve()
					.bodyToMono(PlayerDto.class)
					.block();
			return player;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "fallo player");
		}
	}
}
