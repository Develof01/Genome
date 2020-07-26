package com.mx.kavak.android.gnomegame.data.database.datasources

import com.mx.kavak.android.data.sources.local.NotificationLocalDataSource
import com.mx.kavak.android.domain.models.Notification
import com.mx.kavak.android.gnomegame.data.database.AppDatabase
import com.mx.kavak.android.gnomegame.data.toDomainNotification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotificationDataSource(db: AppDatabase): NotificationLocalDataSource {

    private val notificationDao = db.notificationDao()

    override suspend fun getNotification(): List<Notification> =
        withContext(Dispatchers.IO) {
            notificationDao.getAll().map { it.toDomainNotification() }
        }

}