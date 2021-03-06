package com.springboot.app.board.services;

import com.springboot.app.board.dto.BoardRequest;
import com.springboot.app.board.dto.BoardResponse;

public interface IBoardService {
	
	BoardResponse create(BoardRequest boardRequest);

	BoardResponse get(Integer id);

}
