package com.example.foodexplorer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();

        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + uploadPath.toString() + "/");
    }
}

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


