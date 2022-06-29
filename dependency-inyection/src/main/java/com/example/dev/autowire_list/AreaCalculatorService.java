package com.example.dev.autowire_list;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AreaCalculatorService {
	
	private static final Logger log = LoggerFactory.getLogger(AreaCalculatorService.class);

	
	@Autowired
	private List<Figure> figures;
	
	public double calcAreas() {
		double area = 0;
		for (Figure figure : figures) {
			area += figure.calculateArea();
		}
		return area;
	}
	
}
