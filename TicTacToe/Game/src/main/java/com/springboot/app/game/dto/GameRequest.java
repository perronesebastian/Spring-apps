package com.springboot.app.game.dto;


public class GameRequest {
	
	private Integer board_id;
	
	private Integer player_id_1;
	
	private Integer player_id_2;
	
	public Integer getBoard_id() {
		return board_id;
	}

	public void setBoard_id(Integer board_id) {
		this.board_id = board_id;
	}

	public Integer getPlayer_id_1() {
		return player_id_1;
	}

	public void setPlayer_id_1(Integer player_id_1) {
		this.player_id_1 = player_id_1;
	}

	public Integer getPlayer_id_2() {
		return player_id_2;
	}

	public void setPlayer_id_2(Integer player_id_2) {
		this.player_id_2 = player_id_2;
	}
}
