package com.mx.kavak.android.data.repository

import com.mx.kavak.android.data.sources.local.NotificationLocalDataSource
import com.mx.kavak.android.domain.models.Notification

class NotificationRepository(private val localDataSource: NotificationLocalDataSource) {

    suspend fun displayNotifications(): List<Notification> =
        localDataSource.getNotification()

}