package com.ecomm.user.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties("userservice")
@Getter
@Setter
public class Configuration {
	private int minimum;
	private int maximum;
}
