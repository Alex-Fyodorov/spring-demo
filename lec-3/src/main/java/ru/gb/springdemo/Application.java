package ru.gb.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	// http://localhost:8080/swagger-ui.html

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
