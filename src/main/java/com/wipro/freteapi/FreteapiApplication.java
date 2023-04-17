package com.wipro.freteapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.wipro.freteapi")
public class FreteapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreteapiApplication.class, args);
	}

}
