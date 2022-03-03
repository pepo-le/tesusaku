package com.pepole.tesusaku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class) // これ
public class TesusakuApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesusakuApplication.class, args);
	}

}
