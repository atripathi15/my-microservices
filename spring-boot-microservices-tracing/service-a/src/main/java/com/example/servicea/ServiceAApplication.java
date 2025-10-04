package com.example.servicea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class ServiceAApplication {

    private static final Logger logger = LoggerFactory.getLogger(ServiceAApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ServiceAApplication.class, args);
    }
}
