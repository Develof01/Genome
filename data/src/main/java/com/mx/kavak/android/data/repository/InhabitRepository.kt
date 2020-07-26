package com.mx.kavak.android.data.repository

import com.mx.kavak.android.data.sources.local.InhabitantLocalDataSource
import com.mx.kavak.android.data.sources.remote.InhabitantRemoteDataSource
import com.mx.kavak.android.domain.dto.response.InhabitantsResponse
import com.mx.kavak.android.domain.models.Inhabitant
import com.mx.kavak.android.domain.models.ResultWrapper

class InhabitRepository(private val remoteDataSource: InhabitantRemoteDataSource,
                        private val localDataSource: InhabitantLocalDataSource
) {

    suspend fun readInhabitants(): ResultWrapper<InhabitantsResponse?> =
        remoteDataSource.readInhabits()

    suspend fun readInhabitantFriends(inhabitantId: Int): List<Inhabitant> =
        localDataSource.readInhabitantFriends(inhabitantId)

    suspend fun changeFavoriteInhabitantStatus(inhabitantId: Int, status: Boolean) {
        localDataSource.changeFavoriteInhabitantStatus(inhabitantId, status)
    }

}