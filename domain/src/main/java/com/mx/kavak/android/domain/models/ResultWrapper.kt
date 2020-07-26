package com.mx.kavak.android.domain.models

sealed class  ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class GenericError(val status: Int? = null,
                            val errorDesc: String?): ResultWrapper<Nothing>()
    class NetworkError(val networkError: String): ResultWrapper<Nothing>()
}