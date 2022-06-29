package com.example.users.controllers;

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

import com.example.users.entity.Address;
import com.example.users.services.AddressService;

@RestController
@RequestMapping("/users/{userID}/profiles/{profileID}/addresses")
public class AddressController {
	
	@Autowired
	private AddressService service;

	@GetMapping
	public ResponseEntity<List<Address>> findAddressesByUserIdAndProfileId(
			@PathVariable("userID") Integer userID,
			@PathVariable("profileID") Integer profileID) {
		return new ResponseEntity<List<Address>>(service.findAddressesByUserIdAndProfileId(userID, profileID), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Address> create(
			@PathVariable("userID") Integer userID,
			@PathVariable("profileID") Integer profileID,
			@RequestBody Address address) {
		return new ResponseEntity<Address>(service.create(userID, profileID, address), HttpStatus.CREATED);
	}
}
