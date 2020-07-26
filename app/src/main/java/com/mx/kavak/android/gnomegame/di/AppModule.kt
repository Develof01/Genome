package com.mx.kavak.android.gnomegame.di

import android.app.Application
import android.content.Context
import com.mx.kavak.android.data.sources.local.InhabitantLocalDataSource
import com.mx.kavak.android.data.sources.local.NotificationLocalDataSource
import com.mx.kavak.android.data.sources.local.UserLocalDataSource
import com.mx.kavak.android.data.sources.remote.InhabitantRemoteDataSource
import com.mx.kavak.android.gnomegame.data.database.AppDatabase
import com.mx.kavak.android.gnomegame.data.database.datasources.InhabitantDataSource
import com.mx.kavak.android.gnomegame.data.database.datasources.NotificationDataSource
import com.mx.kavak.android.gnomegame.data.database.datasources.UserDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application): AppDatabase = AppDatabase.build(app)

    @Provides
    fun userLocalDataSourceProvider(db: AppDatabase): UserLocalDataSource =
        UserDataSource(db)

    @Provides
    fun inhabitantLocalDataSourceProvider(db: AppDatabase): InhabitantLocalDataSource =
        InhabitantDataSource(db)

    @Provides
    fun notificationLocalDataSourceProvider(db: AppDatabase): NotificationLocalDataSource =
        NotificationDataSource(db)

    @Provides
    fun inhabitantRemoteDataSourceProvider(@ApplicationContext context: Context): InhabitantRemoteDataSource =
        com.mx.kavak.android.gnomegame.data.api.datasources.InhabitantDataSource(context)

}