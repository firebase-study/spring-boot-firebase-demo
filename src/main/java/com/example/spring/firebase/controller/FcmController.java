package com.example.spring.firebase.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.firebase.dto.PushRequestDto;
import com.example.spring.firebase.service.MyFirebaseService;

@RestController
@RequestMapping("/")
public class FcmController {

	private final MyFirebaseService myFirebaseService;

	public FcmController(MyFirebaseService myFirebaseService) {
		this.myFirebaseService = myFirebaseService;
	}

	@PostMapping("/send-fcm")
	public void sendFcm(@RequestBody PushRequestDto pushRequestDto) throws Exception {

		String response = this.myFirebaseService.sendMessage(pushRequestDto);

		System.out.println("response:" + response);

	}
	

}
