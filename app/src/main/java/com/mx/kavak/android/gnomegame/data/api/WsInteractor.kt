package com.mx.kavak.android.gnomegame.data.api

import com.mx.kavak.android.domain.dto.response.InhabitantsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET


interface WsInteractor {

    @GET("master/data.json")
    fun readInhabitants(): Deferred<InhabitantsResponse?>

}