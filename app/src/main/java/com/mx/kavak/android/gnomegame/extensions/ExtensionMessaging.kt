package com.mx.kavak.android.gnomegame.extensions

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.mx.kavak.android.gnomegame.R

fun Context.displayNotification(messageTitle: String, messageBody: String) {

    val defaultSoundUri = RingtoneManager.getDefaultUri(
        RingtoneManager.TYPE_NOTIFICATION
    )
    val notificationBuilder =
        NotificationCompat.Builder(this, "")
            .setSmallIcon(R.drawable.ic_gnomo)
            .setColor(ContextCompat.getColor(this, R.color.white))
            .setContentTitle(messageTitle)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)

    val notificationManager =
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            "",
            "Channel human readable title",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
    }

    notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
}