package com.example.spring.firebase.dto;

public class User {
	
	public String userName;

	public String email;

	public String deviceToken;
	
	public User() {
	}

	public User(String userName, String email, String deviceToken) {
		this.userName = userName;
		this.email = email;
		this.deviceToken = deviceToken;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

}
