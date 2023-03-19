package com.ashish.product.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class CustomLoadBalancerConfiguration {
	@Bean
	ServiceInstanceListSupplier serviceInstanceListSupplier(ConfigurableApplicationContext context, @Qualifier("restTemplate") RestTemplate restTemplate) {
		return ServiceInstanceListSupplier
				.builder()
				.withBlockingDiscoveryClient()
				.withBlockingHealthChecks(restTemplate)
				.build(context);
	}
}

