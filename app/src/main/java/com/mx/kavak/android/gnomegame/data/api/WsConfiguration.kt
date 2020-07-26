package com.mx.kavak.android.gnomegame.data.api

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mx.kavak.android.gnomegame.BuildConfig
import com.mx.kavak.android.gnomegame.utils.AssetsPropertyReader
import com.mx.kavak.android.gnomegame.utils.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object WsConfiguration {

    fun getInteractor(context: Context): WsInteractor {
        return Retrofit.Builder()
            .baseUrl(AssetsPropertyReader(context).getProperty()!![BuildConfig.URL_BASE].toString())
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .run {
                create(WsInteractor::class.java)
            }
    }

    private val okHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .connectTimeout(Constant.TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constant.TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constant.TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(this).build()
    }

}