package com.example.demo;

import org.apache.http.impl.conn.tsccm.WaitingThread;
import org.apache.tomcat.jni.Thread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
 
import com.example.demo.aspect.RunTimeMonitor;

import Client.HttpPostReq;

@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
public class DemoApplication {
	@Autowired
	RunTimeMonitor monitor;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		Calculator calculator = context.getBean(Calculator.class);
		calculator.setDbConnection(context.getBean(MongoDb.class));
		 
		
		
 
	 new java.lang.Thread(new HttpPostReq()).start();
		
	}
}
