package com.ecomm.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.user.config.Configuration;
import com.ecomm.user.model.UserConfiguration;

@RestController
@RequestMapping("/api/v1")
public class UserConfigController {
	
	@Autowired
	private Configuration configuration;

	@GetMapping("/config")
	public UserConfiguration retrieveLimitsFromConfigurations() {
		UserConfiguration limitConfiguration = new UserConfiguration(configuration.getMaximum(), 
				configuration.getMinimum());
		return limitConfiguration;
	}

}
