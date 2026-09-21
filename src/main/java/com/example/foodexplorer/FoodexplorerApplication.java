package com.example.foodexplorer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class FoodexplorerApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context =
		    SpringApplication.run(FoodexplorerApplication.class, args);

		Environment env = context.getEnvironment();

        String PORT = env.getProperty("server.port", "8080");

		System.out.println("Servidor rodando na porta: " + PORT);
	}
}
