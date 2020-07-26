package com.mx.kavak.android.gnomegame.di

import com.mx.kavak.android.data.repository.InhabitantRepository
import com.mx.kavak.android.data.repository.NotificationRepository
import com.mx.kavak.android.data.repository.UserRepository
import com.mx.kavak.android.data.sources.local.InhabitantLocalDataSource
import com.mx.kavak.android.data.sources.local.NotificationLocalDataSource
import com.mx.kavak.android.data.sources.local.UserLocalDataSource
import com.mx.kavak.android.data.sources.remote.InhabitantRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class DataModule {

    @Provides
    fun userRepositoryProvider(localDataSource: UserLocalDataSource) =
        UserRepository(localDataSource)

    @Provides
    fun inhabitantRepositoryProvider(
        remoteDataSource: InhabitantRemoteDataSource,
        localDataSource: InhabitantLocalDataSource) =
        InhabitantRepository(remoteDataSource, localDataSource)

    @Provides
    fun notificationRepositoryProvider(localDataSource: NotificationLocalDataSource) =
        NotificationRepository(localDataSource)

}