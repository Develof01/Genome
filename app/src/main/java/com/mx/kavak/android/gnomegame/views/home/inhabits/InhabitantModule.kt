package com.mx.kavak.android.gnomegame.views.home.inhabits

import com.mx.kavak.android.data.repository.InhabitantRepository
import com.mx.kavak.android.usecases.InhabitantsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class InhabitantModule {

    @Provides
    fun inhabitantsUseCaseProvider(repository: InhabitantRepository) =
        InhabitantsUseCase(repository)

}