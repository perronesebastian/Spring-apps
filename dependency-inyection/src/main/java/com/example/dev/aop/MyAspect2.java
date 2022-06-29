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
@Order(0)
public class MyAspect2 {
	
	private static final Logger log = LoggerFactory.getLogger(MyAspect2.class);
	
	@Before("PointcutExample.targetObjectMethods()") //Entre parentesis va el pointcut
	public void Before(JoinPoint joinPoint) {
		log.info("1 Method Name {}", joinPoint.getSignature().getName());
		log.info("1 Method type {}", joinPoint.getSignature().getDeclaringTypeName());
		log.info("1 Modifiers {}", joinPoint.getSignature().getModifiers());
		log.info("1 Is public {}", Modifier.isPublic(joinPoint.getSignature().getModifiers()));
		log.info("1 Arguments {}", joinPoint.getArgs());
		log.info("1 Before advice");
	}
}

