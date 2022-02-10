package com.oceanbrasil.jornada_android_fev22_pushnotification

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(p0: RemoteMessage) {
        Log.d("FIREBASE", "Remote message: $p0")
    }

    override fun onNewToken(p0: String) {
        Log.d("FIREBASE", "New token: $p0")
    }
}
