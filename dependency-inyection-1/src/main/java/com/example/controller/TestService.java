package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	
	@Autowired
	private DbRepository dbRepository;
	
	public String test() {
		dbRepository.test();
		return "Hola, soy el servicio";
	}

}
