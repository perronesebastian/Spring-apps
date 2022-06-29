package com.example.users.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.users.entity.Profile;
import com.example.users.services.ProfileService;

@RequestMapping("/users/{userID}/profiles")
@RestController
public class ProfileController {
	
	@Autowired
	private ProfileService service;
	
	@GetMapping("/{profileID}")
	public ResponseEntity<Profile> getById(@PathVariable("userID") Integer userID, @PathVariable("profileID") Integer profileID) {
		return new ResponseEntity<Profile>(service.getByUserIdAndProfileId(userID, profileID), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Profile> create(@PathVariable("userID") Integer userID, @RequestBody Profile profile){
		return new ResponseEntity<Profile>(service.create(userID, profile), HttpStatus.CREATED);
	}

}
