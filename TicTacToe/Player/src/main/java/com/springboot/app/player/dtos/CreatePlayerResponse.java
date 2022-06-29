package com.springboot.app.player.dtos;

import java.util.ArrayList;
import java.util.List;

public class CreatePlayerResponse {
	
	private Integer id;
	
	private String username;
	
	@SuppressWarnings("unused")
	private List<CoordinateDto> coordinates;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public List<CoordinateDto> getCoordinates() {
		return coordinates = new ArrayList<>();
	}
}
