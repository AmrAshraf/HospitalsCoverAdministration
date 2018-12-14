package com.hospitalscoveradministration.FireBase;

import android.app.Service;
import android.util.Pair;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String,String> data=remoteMessage.getData();
        String value=data.get("key");

    }
}
