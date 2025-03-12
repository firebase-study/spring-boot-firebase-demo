package com.example.spring.firebase.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.spring.firebase.dto.RequestDto;
import com.example.spring.firebase.dto.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidConfig.Priority;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.ApsAlert;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Message.Builder;
import com.google.firebase.messaging.Notification;

@Service
public class MyFirebaseService {

	private final FirebaseMessaging firebaseMessaging;

	private final FirebaseDatabase firebaseDatabase;

	public MyFirebaseService(FirebaseMessaging firebaseMessaging, FirebaseDatabase firebaseDatabase) {
		this.firebaseMessaging = firebaseMessaging;
		this.firebaseDatabase = firebaseDatabase;
	}

	// 通知メッセージを送信する
	public String sendMessage(RequestDto requestDto) throws FirebaseMessagingException {

		this.sendMessageToUsers(requestDto);

		this.saveNotification(requestDto);

		return "ok";

	}

	private void sendMessageToUsers(RequestDto requestDto) {
		DatabaseReference ref = firebaseDatabase.getReference().child("users");

		ref.addListenerForSingleValueEvent(new ValueEventListener() {

			@Override
			public void onDataChange(DataSnapshot snapshot) {
				// User 情報を取得する
				Iterable<DataSnapshot> childrens = snapshot.getChildren();
				childrens.forEach(snapshotObject -> {
					// ユーザーごとに送信する
					User user = snapshotObject.getValue(User.class);
					if (!user.getDeviceToken().isEmpty()) {
						Builder messageBuilder = buildMessage(requestDto);
						sendMessage(user.getDeviceToken(), messageBuilder);
					}
				});
			}

			@Override
			public void onCancelled(DatabaseError error) {
				System.out.println("Failed to read data: " + error.getMessage());
			}
		});
	}

	/**
	 *  
	 * @param deviceToken
	 * @param messageBuilder
	 */
	private void sendMessage(String deviceToken, Builder messageBuilder) {
		Message message = messageBuilder.setToken(deviceToken).build();
		System.out.println("token: " + deviceToken);
		try {
			//メッセージを送信する
			this.firebaseMessaging.send(message);
		} catch (FirebaseMessagingException e) {
			e.printStackTrace();
			System.err.println(" error!");
		}
	}

	private Builder buildMessage(RequestDto requestDto) {

		Map<String, Object> dataMap = requestDto.getDataMap();

		Notification notification = Notification.builder() //
				.setTitle(requestDto.getTitle()) //
				.setBody(requestDto.getBody()) //
				.build();

		AndroidNotification androidNotification = AndroidNotification.builder()
				.setTitle(requestDto.getTitle())
				.setBody(requestDto.getBody())
				.setClickAction(requestDto.getClickAction())
				.build();

		AndroidConfig androidConfig = AndroidConfig.builder()
				.setNotification(androidNotification)
				.setPriority(Priority.HIGH)
				.setTtl(4500)
				.build();

		ApsAlert apsAlert = ApsAlert.builder()
				.setTitle(requestDto.getTitle()) //
				.setBody(requestDto.getBody()) //
				.build();

		Aps aps = Aps.builder() //
				.setContentAvailable(true) //
				.setAlert(apsAlert)//
				.setCategory(requestDto.getCategory())
				.build();

		ApnsConfig apnsConfig = ApnsConfig.builder() //
				.setAps(aps) //
				.putAllCustomData(dataMap) //
				.build();

		Builder messageBuilder = Message.builder()
				.setNotification(notification)
				.setAndroidConfig(androidConfig)
				.setApnsConfig(apnsConfig);

		return messageBuilder;
	}

	// 送信内容を保存する
	private void saveNotification(RequestDto requestDto) {
		DatabaseReference ref = firebaseDatabase.getReference().child("push-request");

		//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHHmmss");
		//		String notificationTime = LocalDateTime.now().format(formatter);

		String key = ref.push().getKey();
		ref.child(key).setValueAsync(requestDto);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("timestamp", ServerValue.TIMESTAMP);
		ref.child(key).updateChildrenAsync(map);

		//		String child = String.valueOf(pushRequestDto.getPushRequestSeq());
		//		ref.child(child).setValueAsync(pushRequestDto);

	}
}
