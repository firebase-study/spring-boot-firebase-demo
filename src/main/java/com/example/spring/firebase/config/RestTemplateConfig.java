package com.example.spring.firebase.config;

import java.io.IOException;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.google.auth.oauth2.GoogleCredentials;

@Configuration
public class RestTemplateConfig {

	@Bean
	RestTemplate restTemplate(final GoogleCredentials credentials) throws IOException {

		credentials.refreshIfExpired();
		String accessToken = credentials.getAccessToken().toString();

		RestTemplate restTemplate = new RestTemplateBuilder(
				rt -> rt.getInterceptors().add((request, body, execution) -> {
					request.getHeaders().add("Authorization", "Bearer " + accessToken);
					return execution.execute(request, body);
				})).build();

		
		return restTemplate;

	}

}
