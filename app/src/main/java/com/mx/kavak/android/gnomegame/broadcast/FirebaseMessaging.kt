package com.mx.kavak.android.gnomegame.broadcast

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.mx.kavak.android.gnomegame.data.database.AppDatabase
import com.mx.kavak.android.gnomegame.data.database.dao.NotificationDao
import com.mx.kavak.android.gnomegame.data.database.entities.Notification
import com.mx.kavak.android.gnomegame.extensions.displayNotification
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FirebaseMessaging: FirebaseMessagingService() {

    @Inject
    lateinit var db: AppDatabase
    private lateinit var notificationDao: NotificationDao

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        notificationDao = db.notificationDao()

        if (remoteMessage.notification != null && remoteMessage.notification!!.body != null) {
            displayNotification("GENOMO", remoteMessage.notification!!.body!!)
            notificationDao.insertNotification(
                Notification(0, remoteMessage.notification!!.title!!, remoteMessage.notification!!.body!!))
        }
    }


    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.v("New Token", p0)
    }

}