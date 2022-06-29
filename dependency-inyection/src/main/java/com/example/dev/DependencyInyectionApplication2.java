package com.example.dev;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

@SpringBootApplication
public class DependencyInyectionApplication2 {
	
	
	private static final Logger log = LoggerFactory.getLogger(DependencyInyectionApplication2.class);

	
	public static void main(String[] args) {
		ExpressionParser parser = new SpelExpressionParser();
		
		Expression expression = parser.parseExpression("11 ne 10");
			
		log.info("Result {}", expression.getValue());
	}
	
}
