package com.oceanbrasil.jornada_android_fev22_pushnotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(p0: String) {
        Log.d("FIREBASE", "New token: $p0")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("FIREBASE", "Remote message: $remoteMessage")

        // Pegar a notificação que foi enviada pela interface do Firebase (no site)
        // Essa notificação está disponível dentro do RemoteMessage (remoteMessage)

        val notification = remoteMessage.notification

        notification?.let {
            val title = it.title
            val body = it.body

            pushNotification(title, body)
        }
    }

    private fun pushNotification(title: String?, body: String?) {
        // Obter o Notification Manager
        // O Notification Manager é um serviço do Android para criar notificações

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Criação do canal de notificação, apenas para Androids API 26 (OREO) ou superior

        val channelId = "OCEAN_PRINCIPAL"

        val channelName = "Ocean - Canal Principal"
        val channelDescription = "Ocean - Canal utilizado para as principais notificações do Ocean"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = channelDescription
            }

            notificationManager.createNotificationChannel(channel)
        }

        // Criação da notificação

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(android.R.drawable.ic_dialog_alert)

        // Envio da notificação

        val notification = notificationBuilder.build()

        val notificationId = 1

        notificationManager.notify(notificationId, notification)
    }
}
