package com.example.dev.autowire_list;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Circle implements Figure {
	
	@Value("${circle.radius}")
	private double radius;
	
	@Override
	public double calculateArea() {
		return Math.PI * Math.pow(radius, 2)/2;
	}

}
