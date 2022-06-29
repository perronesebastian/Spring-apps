package com.example.users.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.users.entity.User;
import com.example.users.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
		
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<Page<User>> getUsers(
			@RequestParam(required = false, value = "page", defaultValue = "0") int page, 
			@RequestParam(required = false, value = "size", defaultValue = "10000") int size) {
		return new ResponseEntity<>(service.getUser(page, size), HttpStatus.OK);
	}
	
	@GetMapping("/usernames")
	public ResponseEntity<Page<String>> getUsernames(
			@RequestParam(required = false, value = "page", defaultValue = "0") int page, 
			@RequestParam(required = false, value = "size", defaultValue = "10000") int size) {
		return new ResponseEntity<>(service.getUsernames(page, size), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(service.getUserById(id), HttpStatus.OK);
	}
	
	@GetMapping("/username/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
		return new ResponseEntity<>(service.getUserByUsername(username), HttpStatus.OK);
	}
	
	@DeleteMapping("/{username}")
	public ResponseEntity<Void> delete(@PathVariable("username") String username) {
		service.deleteUser(username);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
