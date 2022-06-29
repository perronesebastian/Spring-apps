package com.example.dev.qualifiers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Perro extends Animal {
	
	public Perro(@Value("Frida")String nombre, @Value("5") Integer edad) {
		super(nombre, edad);
	}

}
