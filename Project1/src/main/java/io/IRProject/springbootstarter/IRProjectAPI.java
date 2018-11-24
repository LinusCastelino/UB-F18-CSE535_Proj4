package io.IRProject.springbootstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//by @SpringBoot Application we are saying that class IRProjectAPI is a type of springboot application
@SpringBootApplication
public class IRProjectAPI {
	public static void main(String[] args) {
		//now to start the servlet container we do following
		SpringApplication.run(IRProjectAPI.class, args);		
	}
}
