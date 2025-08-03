package com.com.Courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;



@SpringBootApplication
@EnableCaching
public class CoursesApplication {

	public static void main(String[] args) {

		SpringApplication.run(CoursesApplication.class, args);
		System.out.println("API user test");

	}

}
