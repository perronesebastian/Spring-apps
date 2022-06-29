package com.example.dev.qualifiers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("pajarito")
public class Pajaro extends Animal implements Volador {
	
	private static final Logger log = LoggerFactory.getLogger(Pajaro.class);

	public Pajaro(@Value("Pajaro Loco")String nombre, @Value("10") Integer edad) {
		super(nombre, edad);
	}

	@Override
	public void Volar() {
		log.info("Soy un pajaro y estoy volando ");
	}

}
