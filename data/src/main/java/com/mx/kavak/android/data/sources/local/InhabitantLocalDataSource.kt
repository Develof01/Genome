package com.mx.kavak.android.data.sources.local

import com.mx.kavak.android.domain.models.Inhabitant

interface InhabitantLocalDataSource {
    suspend fun readInhabitantFriends(idInhabitan: Int): List<Inhabitant>
    suspend fun changeFavoriteInhabitantStatus(inhabitantId: Int, status: Boolean)
}

