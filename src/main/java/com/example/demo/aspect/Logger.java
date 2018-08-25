package com.example.demo.aspect;

 

public class Logger {
	private long startRunTime;
	private static volatile Logger log;
	private static Object syncObject = new Object();
 
	private Logger() {}
			
	public Logger getLoggerInstance() {
		
		if (log==null) {			
			synchronized (syncObject) {				
				if (log==null) {				
					log = new Logger();					
				}								
			}			
		}	
		return log;		
	}
	
	public void monitorStartTimeOfMethod() {
		startRunTime = System.currentTimeMillis();
	}

 
	public void monitorFunctionRunTime() {
		long endRunTime = System.currentTimeMillis() - startRunTime;
		System.out.println("Debug: Runtime of " + "" + " = " + endRunTime);
		// print to log if a method run above 0.5 seconds
		if (endRunTime > 500) {
			//log.warning("The method " + "" + " run more than 0.5 seconds");
			System.out.println("The method " + "" + " run more than 0.5 seconds");
		}
	}
	
	
}