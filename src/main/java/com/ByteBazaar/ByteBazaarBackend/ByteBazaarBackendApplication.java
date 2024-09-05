package com.ByteBazaar.ByteBazaarBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.ByteBazaar.ByteBazaarBackend.repository")
public class ByteBazaarBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ByteBazaarBackendApplication.class, args);
	}

}
