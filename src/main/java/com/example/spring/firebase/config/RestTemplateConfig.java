package com.example.spring.firebase.config;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	@Bean
	RestTemplate restTemplate() {

		// RestTemplate
		RestTemplate restTemplate = new RestTemplateBuilder().setReadTimeout(Duration.ofMillis(31000))
				.setConnectTimeout(Duration.ofMillis(5000)).build();

		return restTemplate;

	}

}
