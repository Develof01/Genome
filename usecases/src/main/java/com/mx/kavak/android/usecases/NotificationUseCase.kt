package com.mx.kavak.android.usecases

import com.mx.kavak.android.data.repository.NotificationRepository
import com.mx.kavak.android.domain.models.Notification

class NotificationUseCase(private val repository: NotificationRepository) {

    suspend fun displayNotifications(): List<Notification> =
        repository.displayNotifications()

}