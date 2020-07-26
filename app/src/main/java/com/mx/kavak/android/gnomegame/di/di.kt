package com.mx.kavak.android.gnomegame.di

import android.app.Application
import com.mx.kavak.android.data.repository.InhabitRepository
import com.mx.kavak.android.data.repository.NotificationRepository
import com.mx.kavak.android.data.repository.UserRepository
import com.mx.kavak.android.data.sources.local.InhabitantLocalDataSource
import com.mx.kavak.android.data.sources.local.NotificationLocalDataSource
import com.mx.kavak.android.data.sources.local.UserLocalDataSource
import com.mx.kavak.android.data.sources.remote.InhabitantRemoteDataSource
import com.mx.kavak.android.domain.models.Inhabitant
import com.mx.kavak.android.gnomegame.data.api.datasources.InhabitantDataSource
import com.mx.kavak.android.gnomegame.data.database.AppDatabase
import com.mx.kavak.android.gnomegame.data.database.datasources.NotificationDataSource
import com.mx.kavak.android.gnomegame.data.database.datasources.UserDataSource
import com.mx.kavak.android.gnomegame.views.home.inhabits.InhabitantsFragment
import com.mx.kavak.android.gnomegame.views.home.inhabits.InhabitantsViewModel
import com.mx.kavak.android.gnomegame.views.home.inhabits.detail.InhabitantDetailFragment
import com.mx.kavak.android.gnomegame.views.home.inhabits.detail.InhabitantDetailViewModel
import com.mx.kavak.android.gnomegame.views.home.notifications.NotificationFragment
import com.mx.kavak.android.gnomegame.views.home.notifications.NotificationViewModel
import com.mx.kavak.android.gnomegame.views.splash.SplashActivity
import com.mx.kavak.android.gnomegame.views.splash.SplashViewModel
import com.mx.kavak.android.usecases.InhabitantsUseCase
import com.mx.kavak.android.usecases.NotificationUseCase
import com.mx.kavak.android.usecases.UserUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {

    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(
            listOf(
                appModule,
                dataModule,
                splashModule,
                inhabitantsModule,
                inhabitantDetailModule,
                notificationModule
            )
        )
    }
}

private val appModule = module {
    single {
        AppDatabase.build(get())
    }
    factory<UserLocalDataSource> { UserDataSource(get()) }
    factory<InhabitantLocalDataSource> {
        com.mx.kavak.android.gnomegame.data.database.datasources.InhabitantDataSource(get()) }
    factory<InhabitantRemoteDataSource> { InhabitantDataSource(get()) }
    factory<NotificationLocalDataSource> { NotificationDataSource(get()) }
}

private val dataModule = module {
    factory { UserRepository(get()) }
    factory { InhabitRepository(get(), get()) }
    factory { NotificationRepository(get()) }
}

private val splashModule = module {
    scope(named<SplashActivity>()) {
        viewModel { SplashViewModel(get()) }
        scoped { UserUseCase(get()) }
    }
}

private val inhabitantsModule = module {
    scope(named<InhabitantsFragment>()) {
        viewModel { InhabitantsViewModel(get(), get()) }
        scoped { InhabitantsUseCase(get()) }
    }
}

private val inhabitantDetailModule = module {
    scope(named<InhabitantDetailFragment>()) {
        viewModel { (inhabitant: Inhabitant) -> InhabitantDetailViewModel(inhabitant, get()) }
        scoped { InhabitantsUseCase(get()) }
    }
}

private val notificationModule = module {
    scope(named<NotificationFragment>()) {
        viewModel { NotificationViewModel(get()) }
        scoped { NotificationUseCase(get()) }
    }
}