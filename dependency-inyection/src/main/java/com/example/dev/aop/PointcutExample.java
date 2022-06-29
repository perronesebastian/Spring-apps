package com.example.dev.aop;

import org.aspectj.lang.annotation.Pointcut;

public class PointcutExample {
	//@Pointcut("execution(* com.example.dev.aop.TargetObject.printMessage(..))")
	//@Pointcut("within(com.example.dev.aop.TargetObject)")
	@Pointcut("within(TargetObject)")
	public void targetObjectMethods() {
	}
}
	
