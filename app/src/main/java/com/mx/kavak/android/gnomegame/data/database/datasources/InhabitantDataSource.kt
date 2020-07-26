package com.mx.kavak.android.gnomegame.data.database.datasources

import com.mx.kavak.android.data.sources.local.InhabitantLocalDataSource
import com.mx.kavak.android.domain.models.Inhabitant
import com.mx.kavak.android.gnomegame.data.database.AppDatabase
import com.mx.kavak.android.gnomegame.data.toDomainInhabitant

class InhabitantDataSource(db: AppDatabase): InhabitantLocalDataSource {

    private val inhabitantDao = db.inhabitantDao()
    private val inhabitantFriendDao = db.inhabitantFriendsDao()

    override suspend fun readInhabitantFriends(idInhabitan: Int): List<Inhabitant> {
        val namesFriends = inhabitantFriendDao.findByInhabitantName(idInhabitan)
        return inhabitantDao.findAllByNames(namesFriends).map { it.toDomainInhabitant(emptyList(), emptyList()) }
    }

    override suspend fun changeFavoriteInhabitantStatus(inhabitantId: Int, status: Boolean) {
        inhabitantDao.updateFavoriteStatus(inhabitantId, status)
    }

}