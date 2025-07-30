package com.com.Courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableCaching
public class CoursesApplication {

	public static void main(String[] args) {
		   Dotenv dotenv = Dotenv.configure()
                   .directory("./")
                   .ignoreIfMissing() 
                   .load();

String clientId = dotenv.get("GOOGLE_CLIENT_ID");
String clientSecret = dotenv.get("GOOGLE_CLIENT_SECRET");

System.out.println("Client ID: " + clientId);
System.out.println("Client Secret: " + clientSecret);
		SpringApplication.run(CoursesApplication.class, args);
	}

}
