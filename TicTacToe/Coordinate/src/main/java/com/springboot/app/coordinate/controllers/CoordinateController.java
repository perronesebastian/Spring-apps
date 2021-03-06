package com.springboot.app.coordinate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.coordinate.dto.CoordinateDto;
import com.springboot.app.coordinate.services.ICoordinateService;

@RestController
@RequestMapping
public class CoordinateController {
	
	@Autowired
	private ICoordinateService coordinateService;
	
	@PostMapping("/coordinate/put")
	public ResponseEntity<CoordinateDto> create(@RequestBody CoordinateDto coordinateTarget) {
		return new ResponseEntity<CoordinateDto>(coordinateService.create(coordinateTarget), HttpStatus.OK);
	}
	
	@GetMapping("/coordinates/player/{playerId}")
	public ResponseEntity<List<CoordinateDto>> get(@PathVariable("playerId") Integer playerId) {
		return new ResponseEntity<List<CoordinateDto>> (coordinateService.get(playerId), HttpStatus.OK);
	}

}
