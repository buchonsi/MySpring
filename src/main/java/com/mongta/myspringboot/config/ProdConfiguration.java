package com.mongta.myspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfiguration {
	
	@Bean
	public String hello() {
		return "운영 MODE";
	}
}
