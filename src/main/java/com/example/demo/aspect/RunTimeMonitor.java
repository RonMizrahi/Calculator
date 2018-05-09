package com.example.demo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Scope(value = "prototype")
public class RunTimeMonitor {
	private long startRunTime;
	private static Logger log = Logger.getLogger(RunTimeMonitor.class.getName());

	@Before("within(com.example.demo..*)")
	public void monitorStartTimeOfMethod() {
		startRunTime = System.currentTimeMillis();
	}

	@After("within(com.example.demo.*)")
	public void monitorFunctionRunTime(JoinPoint joinPoint) {
		long endRunTime = System.currentTimeMillis() - startRunTime;
		System.out.println("Debug: Runtime of " + joinPoint.getSignature().getName() + " = " + endRunTime);
		// print to log if a method run above 0.5 seconds
		if (endRunTime > 500) {
			log.warning("The method " + joinPoint.getSignature().getName() + " run more than 0.5 seconds");
		}
	}
}
