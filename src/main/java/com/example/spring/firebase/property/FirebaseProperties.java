package com.example.spring.firebase.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotEmpty;

@Validated
@Component
@ConfigurationProperties(prefix = "firebase")
public class FirebaseProperties {
	
	@NotEmpty
	private String serviceAccountFileName;
	
	@NotEmpty
	private String realtimeDatabaseUrl;

	public String getServiceAccountFileName() {
		return serviceAccountFileName;
	}

	public void setServiceAccountFileName(String serviceAccountFileName) {
		this.serviceAccountFileName = serviceAccountFileName;
	}

	public String getRealtimeDatabaseUrl() {
		return realtimeDatabaseUrl;
	}

	public void setRealtimeDatabaseUrl(String realtimeDatabaseUrl) {
		this.realtimeDatabaseUrl = realtimeDatabaseUrl;
	}
	
}