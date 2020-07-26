package com.mx.kavak.android.gnomegame.views.splash

import com.mx.kavak.android.data.repository.UserRepository
import com.mx.kavak.android.usecases.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class SplashModule {

    @Provides
    fun userUserCase(repository: UserRepository) =
        UserUseCase(repository)

}