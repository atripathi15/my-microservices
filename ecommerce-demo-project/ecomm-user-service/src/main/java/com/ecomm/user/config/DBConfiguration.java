package com.ecomm.user.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfiguration {
	
	   @Value("${spring.datasource.driver-class-name}")
	   private String driverClassName;
	   @Value("${spring.datasource.url}")
	   private String dbURL;
	   @Value("${spring.datasource.username}")
	   private String dbUserName;
	   @Value("${spring.datasource.password}")
	   private String dbPassword;

    @Bean
    DataSource getDataSource()	    {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driverClassName);
        dataSourceBuilder.url(dbURL);
        dataSourceBuilder.username(dbUserName);
        dataSourceBuilder.password(dbPassword);
        return dataSourceBuilder.build();
    }
}
