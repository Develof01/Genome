package com.mx.kavak.android.usecases

import com.mx.kavak.android.data.repository.InhabitRepository
import com.mx.kavak.android.domain.dto.response.InhabitantsResponse
import com.mx.kavak.android.domain.models.Inhabitant
import com.mx.kavak.android.domain.models.ResultWrapper

class InhabitantsUseCase(private val repository: InhabitRepository) {

    suspend fun readInhabits(): ResultWrapper<InhabitantsResponse?> =
        repository.readInhabitants()

    suspend fun readInhabitantFriends(inhabitantId: Int): List<Inhabitant> =
        repository.readInhabitantFriends(inhabitantId)

    suspend fun changeFavoriteInhabitantStatus(inhabitantId: Int, status: Boolean) {
        repository.changeFavoriteInhabitantStatus(inhabitantId, status)
    }


}