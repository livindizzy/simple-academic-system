package com.academic.simple_academic_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SimpleAcademicSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleAcademicSystemApplication.class, args);
	}

}
