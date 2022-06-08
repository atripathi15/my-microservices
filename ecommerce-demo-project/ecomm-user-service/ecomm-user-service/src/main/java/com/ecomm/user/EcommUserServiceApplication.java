package com.ecomm.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EcommUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommUserServiceApplication.class, args);
	}

}
