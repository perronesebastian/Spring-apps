package com.springboot.app.game.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "games")
public class GameEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "turn")
	private Integer turn;
	
	@Column(name = "board_id")
	private Integer board_id;
	
	@Column(name = "player_id_1")
	private Integer player_id_1;
	
	@Column(name = "player_id_2")
	private Integer player_id_2;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getTurn() {
		return turn;
	}

	public void setTurn(Integer turn) {
		this.turn = turn;
	}
	
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

	private static final long serialVersionUID = -1566777733817467961L;
	
}
