package com.example.servicea.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    private final RestTemplate restTemplate;

    public HelloController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/service-a/call")
    public String callServiceB() {
        logger.info("Received request in Service A");
        String response = restTemplate.getForObject("http://localhost:8082/service-b/process", String.class);
        logger.info("Response from Service B: {}", response);
        return "Service A processed -> " + response;
    }
}
