package com.example.spring.firebase.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.firebase.dto.FcmRequest;
import com.example.spring.firebase.service.MyFirebaseService;

@RestController
@RequestMapping("/")
public class FcmController {

	private final MyFirebaseService myFirebaseService;

	public FcmController(MyFirebaseService myFirebaseService) {
		this.myFirebaseService = myFirebaseService;
	}

	@PostMapping("/send-fcm")
	public void sendFcm(@RequestBody FcmRequest fcmRequest) throws Exception {

		String response = this.myFirebaseService.sendMessage(fcmRequest);

		System.out.print("response:" + response);

	}

}
