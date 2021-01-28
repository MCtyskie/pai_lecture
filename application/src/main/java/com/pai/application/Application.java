package com.pai.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
//http://localhost:8081/swagger-ui.html
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
