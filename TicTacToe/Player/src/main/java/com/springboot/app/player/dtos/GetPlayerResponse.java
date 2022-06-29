package com.springboot.app.player.dtos;

import java.util.List;

public class GetPlayerResponse {
	
	private Integer id;
	
	private String username;
	
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
		return coordinates;
	}

	public void setCoordinates(List<CoordinateDto> coordinates) {
		this.coordinates = coordinates;
	}
}
