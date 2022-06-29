package com.example.dev.qualifiers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class Nido {	
	
	private static final Logger log = LoggerFactory.getLogger(Nido.class);
	
	@Autowired
	@Qualifier("pajarito")
	public Animal animal;
	
	public void imprimir() {
		log.info("Soy un nido y tengo un animal que se llama {}", animal.getNombre());
	}
}
