package com.springboot.app.board.dto;

public class BoardResponse {
	
	private Integer id;
	
	private Integer dimension;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDimension() {
		return dimension;
	}

	public void setDimension(Integer dimension) {
		this.dimension = dimension;
	}
}
