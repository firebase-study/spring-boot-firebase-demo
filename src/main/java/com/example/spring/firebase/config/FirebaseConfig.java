package com.example.spring.firebase.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.example.spring.firebase.property.FirebaseProperties;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

@Configuration
public class FirebaseConfig {

	private final FirebaseProperties firebaseProperties;

	private final ResourceLoader resourceLoader;

	public FirebaseConfig(FirebaseProperties firebaseProperties, ResourceLoader resourceLoader) {
		this.firebaseProperties = firebaseProperties;
		this.resourceLoader = resourceLoader;
	}
	
	@Bean
	FirebaseApp firebaseApp() throws Exception {
		
		//FileInputStream refreshToken = new FileInputStream("src/main/resources/service-account-adminsdk.json");
		Resource serviceAccount = resourceLoader.getResource(firebaseProperties.getServiceAccountFileName());
		
		FirebaseOptions options = FirebaseOptions.builder()
			    .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
			    .setDatabaseUrl(firebaseProperties.getRealtimeDatabaseUrl())
			    .build();

		List<FirebaseApp> firebaseAppList = FirebaseApp.getApps();
		if (firebaseAppList.isEmpty()) {
			return FirebaseApp.initializeApp(options, "spring-boot-firebase-demo");
		}

		return firebaseAppList.get(0);

	}

	@Bean
	FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
		return FirebaseMessaging.getInstance(firebaseApp);
	}

	@Bean
	FirebaseDatabase firebaseDatabase(FirebaseApp firebaseApp) {
		return FirebaseDatabase.getInstance(firebaseApp);
	}

}
