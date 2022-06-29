package com.example.dev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.dev.aop.TargetObject;
import com.example.dev.autowire_list.AreaCalculatorService;
import com.example.dev.lifecycle.ExplicitBean;
import com.example.dev.lifecycle.LifeCycleBean;
import com.example.dev.profiles.EnviromentService;
import com.example.dev.qualifiers.Animal;
import com.example.dev.qualifiers.Nido;
import com.example.dev.qualifiers.Perro;
import com.example.dev.scopes.ScopeExample;


@SpringBootApplication
public class DependencyInyectionApplication {
	
	private static final Logger log = LoggerFactory.getLogger(DependencyInyectionApplication.class);
	
	
//	@Bean(initMethod = "init", destroyMethod = "destroy")
//	public ExplicitBean getBean(){
//		return new ExplicitBean();
//	}
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context =  SpringApplication.run(DependencyInyectionApplication.class, args);
		
		TargetObject targetObject = context.getBean(TargetObject.class);
		
		targetObject.printMessage("HOLAA");
		
		targetObject.foo();
	} 
					
}
