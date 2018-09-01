package com.example.demo;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import com.example.demo.aspect.RunTimeMonitor;
import Client.Client;
import Database.User;

@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
public class DemoApplication {
	@Autowired
	RunTimeMonitor monitor;

	public static void main(String[] args) throws InterruptedException {

		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		// Not a spring way, but we did it for Stratagy design pattern.
		CalculatorApi calculator = context.getBean(CalculatorApi.class);
		calculator.setDbConnection(context.getBean(MongoDb.class));
		// calculator.setDbConnection(context.getBean(LocalDb.class));

		TimeUnit.SECONDS.sleep(4);
		// Client integrated in server code/console only for convenience
		new Thread(new Client(User.builder("http://localhost", "8080").build())).start();

	}
}
