package com.ashish.product.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class ServiceConfig {
	
	@Bean
    GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("com.ecomm.product")
                .pathsToMatch("/product/**")
                .build();
    }

    @Bean
    OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Product service API")
                        .description("Product Service for Ecomm App")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }
    
    @Bean
    @LoadBalanced
    RestTemplate getRestTemplate() {
    	return new RestTemplate();
    }


}
