package com.example.serviceb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessController {

    private static final Logger logger = LoggerFactory.getLogger(ProcessController.class);

    @GetMapping("/service-b/process")
    public String process() {
        logger.info("Processing request in Service B");
        return "Response from Service B";
    }
}
