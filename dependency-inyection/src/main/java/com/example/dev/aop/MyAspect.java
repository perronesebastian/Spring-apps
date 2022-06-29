package com.example.dev.aop;

import java.lang.reflect.Modifier;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class MyAspect {
	
	private static final Logger log = LoggerFactory.getLogger(MyAspect.class);
	
	@Before("PointcutExample.targetObjectMethods()") //Entre parentesis va el pointcut
	public void Before(JoinPoint joinPoint) {
		log.info("Method Name {}", joinPoint.getSignature().getName());
		log.info("Method type {}", joinPoint.getSignature().getDeclaringTypeName());
		log.info("Modifiers {}", joinPoint.getSignature().getModifiers());
		log.info("Is public {}", Modifier.isPublic(joinPoint.getSignature().getModifiers()));
		log.info("Arguments {}", joinPoint.getArgs());
		log.info("Before advice");
	}
}
