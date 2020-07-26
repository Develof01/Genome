package com.mx.kavak.android.gnomegame.views.home.inhabits.detail

import com.mx.kavak.android.data.repository.InhabitantRepository
import com.mx.kavak.android.usecases.InhabitantsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Named

@Module
@InstallIn(ActivityRetainedComponent::class)
class InhabitantDetailModule {

    @Provides
    @Named("inhabitant")
    fun getInhabitant() = InhabitantDetailFragment.inhabitant

}