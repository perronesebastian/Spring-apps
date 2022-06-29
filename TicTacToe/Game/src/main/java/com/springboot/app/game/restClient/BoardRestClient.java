package com.springboot.app.game.restClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.springboot.app.game.dto.BoardDto;

@Service
public class BoardRestClient {
	
	@Autowired
	@Qualifier("BoardWebClient")
	private WebClient webClient;
	
	public BoardDto get(Integer id) {
		try {
			BoardDto board = webClient.method(HttpMethod.GET)
					.uri("/board/id/" + id)
					.retrieve()
					.bodyToMono(BoardDto.class)
					.block();
			return board;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "fallo board");
		}
	}

}
