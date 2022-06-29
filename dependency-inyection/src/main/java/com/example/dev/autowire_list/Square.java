package com.example.dev.autowire_list;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Square implements Figure{
	
	@Value("${square.side}")
	private double side;
	
	@Override
	public double calculateArea() {
		return side*side;
	}

}
