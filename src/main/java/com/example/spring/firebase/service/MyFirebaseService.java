package com.example.spring.firebase.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.spring.firebase.dto.PushRequestDto;
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
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Message.Builder;
import com.google.firebase.messaging.Notification;

@Service
public class MyFirebaseService {

	//private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final FirebaseMessaging firebaseMessaging;

	private final FirebaseDatabase firebaseDatabase;

	public MyFirebaseService(FirebaseMessaging firebaseMessaging, FirebaseDatabase firebaseDatabase) {
		this.firebaseMessaging = firebaseMessaging;
		this.firebaseDatabase = firebaseDatabase;
	}

	// 通知メッセージを送信する
	public String sendMessage(PushRequestDto pushRequestDto) throws FirebaseMessagingException {

		// 01:iPhoneApp. 02:AndroidApp
		if ("01".equals(pushRequestDto.getTerminalType())) {
			this.sendIphoneMessage(pushRequestDto);
		} else if ("02".equals(pushRequestDto.getTerminalType())) {
			this.sendAndroidMessage(pushRequestDto);
		}

		this.saveNotification(pushRequestDto);

		return "ok";

	}

	/**
	 * AndroidAppへメッセージを送信する
	 * @param pushRequestDto
	 * @param appInstanceTokenList
	 */
	private void sendAndroidMessage(PushRequestDto pushRequestDto) {

		AndroidNotification notification = AndroidNotification.builder()
				.setTitle(pushRequestDto.getTitle())
				.setBody(pushRequestDto.getPopupText())
				.build();

		AndroidConfig androidConfig = AndroidConfig.builder()
				.setNotification(notification)
				.setPriority(Priority.HIGH)
				.setTtl(4500)
				.build();

		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("type", "targeted");
		dataMap.put("content_type", "text/plain");
		dataMap.put("platform", pushRequestDto.getTerminalType());
		dataMap.put("content", pushRequestDto.getContent());

		Builder messageBuilder = Message.builder()
				.setAndroidConfig(androidConfig)
				.putAllData(dataMap);

		this.sendMessage(messageBuilder, this.firebaseMessaging);

	}

	/**
	 * iPhoneAppへメッセージを送信する
	 * @param pushRequestDto
	 * @param appInstanceTokenList
	 */
	private void sendIphoneMessage(PushRequestDto pushRequestDto) {

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("type", "targeted");
		dataMap.put("content_type", "text/plain");
		dataMap.put("platform", pushRequestDto.getTerminalType());
		dataMap.put("content", pushRequestDto.getContent());

		Notification notification = Notification.builder() //
				.setTitle(pushRequestDto.getTitle()) //
				.setBody(pushRequestDto.getPopupText()) //
				.build();

		//		ApsAlert apsAlert = ApsAlert.builder()
		//				.setTitle(pushRequestDto.getTitle()) //
		//				.setBody(pushRequestDto.getPopupText()) //
		//				.build();
		//
		//		Aps aps = Aps.builder() //
		//				.setContentAvailable(true) //
		//				.setAlert(apsAlert)//
		//				.build();

		ApnsConfig apnsConfig = ApnsConfig.builder() //
				//.setAps(aps) //
				.putAllCustomData(dataMap) //
				.build();

		Builder messageBuilder = Message.builder()
				.setNotification(notification)
				.setApnsConfig(apnsConfig);

		this.sendMessage(messageBuilder, this.firebaseMessaging);
	}

	// ユーザーごとに送信する
	private void sendMessage(Builder messageBuilder, FirebaseMessaging firebaseMessaging) {
		DatabaseReference ref = firebaseDatabase.getReference().child("users");

		ref.addListenerForSingleValueEvent(new ValueEventListener() {

			@Override
			public void onDataChange(DataSnapshot snapshot) {
				// User 情報を取得する
				Iterable<DataSnapshot> childrens = snapshot.getChildren();
				childrens.forEach(snapshotObject -> {
					User user = snapshotObject.getValue(User.class);
					if (!user.getAppInstanceToken().isEmpty()) {
						Message message = messageBuilder.setToken(user.getAppInstanceToken()).build();
						System.out.println("token: " + user.getAppInstanceToken());
						try {
							//メッセージを送信する
							firebaseMessaging.send(message);
						} catch (FirebaseMessagingException e) {
							e.printStackTrace();
							System.err.println(" error!");
						}
					}
				});
			}

			@Override
			public void onCancelled(DatabaseError error) {
				System.out.println("Failed to read data: " + error.getMessage());
			}
		});
	}

	// 送信内容を保存する
	private void saveNotification(PushRequestDto pushRequestDto) {
		DatabaseReference ref = firebaseDatabase.getReference().child("push-request");

		//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHHmmss");
		//		String notificationTime = LocalDateTime.now().format(formatter);

		String key = ref.push().getKey();
		ref.child(key).setValueAsync(pushRequestDto);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("timestamp", ServerValue.TIMESTAMP);
		ref.child(key).updateChildrenAsync(map);

		//		String child = String.valueOf(pushRequestDto.getPushRequestSeq());
		//		ref.child(child).setValueAsync(pushRequestDto);

	}

}
