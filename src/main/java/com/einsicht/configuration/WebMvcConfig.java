package com.einsicht.configuration;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//		return bCryptPasswordEncoder;
//	}
	
//	@Bean
//	@Primary
//	public DataSource dataSource() {
//	    return DataSourceBuilder
//	        .create()
//	        .username("root")
//	        .password("root")
//	        .url("jdbc:mysql://localhost/invensistest?autoReconnect=true")
//	        .driverClassName("com.mysql.jdbc.Driver")
//	        .build();
//	}
}