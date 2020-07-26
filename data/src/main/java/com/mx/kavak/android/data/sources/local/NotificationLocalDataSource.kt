package com.mx.kavak.android.data.sources.local

import com.mx.kavak.android.domain.models.Notification

interface NotificationLocalDataSource {

    suspend fun getNotification(): List<Notification>

}