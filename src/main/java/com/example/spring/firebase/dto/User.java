package com.example.spring.firebase.dto;


public class User {

//	public String uId;

	public String userName;

	public String email;

	public String appInstanceToken;

	public User() {
	}

	public User(String appInstanceToken, String userName, String email) {
		this.appInstanceToken = appInstanceToken;
		this.userName = userName;
		this.email = email;
	}

//	public String getuId() {
//		return uId;
//	}
//
//	public void setuId(String uId) {
//		this.uId = uId;
//	}

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

	public String getAppInstanceToken() {
		return appInstanceToken;
	}

	public void setAppInstanceToken(String appInstanceToken) {
		this.appInstanceToken = appInstanceToken;
	}

}
