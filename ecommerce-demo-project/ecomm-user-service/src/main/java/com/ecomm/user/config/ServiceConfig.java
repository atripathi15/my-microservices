package com.ecomm.user.config;

import java.util.Collections;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ServiceConfig {
	
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select().paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.ecomm.user"))
				.build()
				.apiInfo(apiMetaData());
	}
	
	private ApiInfo apiMetaData() {
        ApiInfo apiInfo = new ApiInfo(
                "User service API",
                "Spring Boot User API for Ecomm App",
                "1.0",
                "Terms of service",
                new Contact("Ashish Tripathi", "https://springframework.com/about/", "ashish@springframework.com"),
               "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                Collections.EMPTY_LIST);
        return apiInfo;
    }	

	@Bean
	public ModelMapper getModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}

}
