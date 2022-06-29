package com.springboot.app.board.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.springboot.app.board.dto.BoardRequest;
import com.springboot.app.board.dto.BoardResponse;
import com.springboot.app.board.entities.BoardEntity;
import com.springboot.app.board.repository.BoardRepository;

@Service
public class BoardService implements IBoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Override
	public BoardResponse create(BoardRequest boardRequest) {
		BoardEntity boardEntity = new BoardEntity();
		boardRepository.save(this.mapperToEntity(boardRequest, boardEntity));
		return this.mapperToResponse(boardEntity);
	}
	
	@Override
	public BoardResponse get(Integer id) {
		BoardEntity boardEntity = boardRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Board with id %s does not exist", id)));
		return this.mapperToResponse(boardEntity);
	}

	private BoardResponse mapperToResponse(BoardEntity boardEntity) {
		BoardResponse boardResponse = new BoardResponse();
		boardResponse.setId(boardEntity.getId());
		boardResponse.setDimension(boardEntity.getDimension());
		return boardResponse;
	}
	
	private BoardEntity mapperToEntity(BoardRequest boardRequest, BoardEntity boardEntity) {
		boardEntity.setDimension(boardRequest.getDimension());
		return boardEntity;
	}

}
