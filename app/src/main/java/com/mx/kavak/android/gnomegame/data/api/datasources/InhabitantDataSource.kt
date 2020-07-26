package com.mx.kavak.android.gnomegame.data.api.datasources

import android.content.Context
import com.mx.kavak.android.data.sources.remote.InhabitantRemoteDataSource
import com.mx.kavak.android.domain.dto.response.InhabitantsResponse
import com.mx.kavak.android.domain.models.ResultWrapper
import com.mx.kavak.android.gnomegame.R
import com.mx.kavak.android.gnomegame.data.api.WsConfiguration
import com.mx.kavak.android.gnomegame.utils.Constant
import retrofit2.HttpException
import java.io.IOException

class InhabitantDataSource(private val context: Context) : InhabitantRemoteDataSource {

    override suspend fun readInhabits(): ResultWrapper<InhabitantsResponse?> {
        return try {
            ResultWrapper.Success(
                WsConfiguration.getInteractor(context).readInhabitants().await()
            )
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ResultWrapper.NetworkError(context.resources.getString(R.string.ws_connection_fail))
                is HttpException -> {
                    val code = throwable.code()
                    ResultWrapper.GenericError(
                        code,
                        context.resources.getString(R.string.ws_http_error)
                    )
                }
                else -> {
                    ResultWrapper.GenericError(
                        Constant.GENERAL_ERROR,
                        context.resources.getString(R.string.inhabits_ws_general_error)
                    )
                }
            }
        }
    }

}