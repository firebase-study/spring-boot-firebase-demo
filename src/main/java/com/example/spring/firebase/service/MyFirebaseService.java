package com.example.spring.firebase.service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import com.example.spring.firebase.dto.FcmRequest;
import com.example.spring.firebase.dto.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;


@Service
public class MyFirebaseService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final FirebaseMessaging firebaseMessaging;

	private final FirebaseDatabase firebaseDatabase;

	public MyFirebaseService(FirebaseMessaging firebaseMessaging, FirebaseDatabase firebaseDatabase) {
		this.firebaseMessaging = firebaseMessaging;
		this.firebaseDatabase = firebaseDatabase;
	}

	// 通知メッセージを送信する
	public String sendMessage(FcmRequest request) throws FirebaseMessagingException {

		AndroidNotification androidNotification = AndroidNotification.builder()
				.setTitle(request.getTitle())
				.setBody(request.getMessage())
				.build();

		AndroidConfig androidConfig = AndroidConfig.builder().setNotification(androidNotification).build();

		// Get user devices(app) instance token 
		List<String> appInstanceTokenList = this.getUserInfo().stream()
				.filter(user -> !user.getAppInstanceToken().isEmpty())
				.map(User::getAppInstanceToken)
				.collect(Collectors.toList());
		
		// send notification
		appInstanceTokenList.stream().distinct().forEach(token -> {
			Message message = Message.builder()
					.setToken(token)
					.setAndroidConfig(androidConfig)
					.build();
			try {
				this.firebaseMessaging.send(message);
				LOGGER.info("send notification successed to device: "+ token);
			} catch (FirebaseMessagingException e) {
				e.printStackTrace();
				LOGGER.error("send notification failed to device : "+ token);
				return;
			}
		});
		
		this.saveNotification(request);
		
		return "ok";
	}

	
	// 全てのユーザー情報を取得する
	private List<User> getUserInfo() {
		List<User> userInfoList = new ArrayList<User>();

		DatabaseReference ref = firebaseDatabase.getReference().child("users");
		ref.addValueEventListener(new ValueEventListener() {

			// User 情報を取得する
			@Override
			public void onDataChange(DataSnapshot snapshot) {
				Iterable<DataSnapshot> childrens = snapshot.getChildren();
				childrens.forEach(snapshotObject -> {
					User user = snapshotObject.getValue(User.class);
					userInfoList.add(user);
				});
			}

			@Override
			public void onCancelled(DatabaseError error) {
				System.out.println("Failed to read data: " + error.getMessage());
			}
		});

		return userInfoList;
	}
 
	
	// 送信内容を保存する
	private void  saveNotification(FcmRequest request) {
		DatabaseReference ref = firebaseDatabase.getReference().child("notification");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmmss");
		String notificationTime = LocalDateTime.now().format(formatter);
		
		ref.child(notificationTime).setValueAsync(request);
		
	}

}
