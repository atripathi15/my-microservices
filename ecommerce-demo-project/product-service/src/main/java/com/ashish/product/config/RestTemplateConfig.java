package com.ashish.product.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
//@RibbonClient(name = "user-service", configuration = RibbonConfig.class)
@LoadBalancerClient(value = "user-service", configuration = CustomLoadBalancerConfiguration.class)
public class RestTemplateConfig {
	
	@LoadBalanced
    @Bean
    @Qualifier("loadBalancedRestTemplate")
    RestTemplate loadBalancedRestTemplate() {
        return new RestTemplate();
    }
	
    @Bean
    @Qualifier("restTemplate")
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
}
