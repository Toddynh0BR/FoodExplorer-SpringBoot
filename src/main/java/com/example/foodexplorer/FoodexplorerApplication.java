package com.example.foodexplorer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class FoodexplorerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =
		     SpringApplication.run(FoodexplorerApplication.class, args);

		Environment env = context.getEnvironment();

        String port = env.getProperty("server.port", "8080");

		System.out.println("Servidor rodando na porta: " + port);
	}

	@GetMapping
	public String health() {
		return "Server rodando";
	}

}
