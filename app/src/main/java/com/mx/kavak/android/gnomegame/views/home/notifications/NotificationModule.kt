package com.mx.kavak.android.gnomegame.views.home.notifications

import com.mx.kavak.android.data.repository.NotificationRepository
import com.mx.kavak.android.usecases.NotificationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class NotificationModule {

    @Provides
    fun notificationUseCaseProvider(repository: NotificationRepository) =
        NotificationUseCase(repository)

}