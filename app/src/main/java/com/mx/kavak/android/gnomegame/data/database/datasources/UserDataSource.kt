package com.mx.kavak.android.gnomegame.data.database.datasources

import android.util.Base64
import com.mx.kavak.android.data.sources.local.UserLocalDataSource
import com.mx.kavak.android.domain.models.User
import com.mx.kavak.android.gnomegame.data.database.AppDatabase
import com.mx.kavak.android.gnomegame.data.toDomainUser
import com.mx.kavak.android.gnomegame.data.toRoomUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserDataSource(db: AppDatabase): UserLocalDataSource {

    private val userDao = db.userDao()

    override suspend fun getLocalUser(): User? =
        withContext(Dispatchers.IO) {
            userDao.findById(0)?.toDomainUser()
        }

    override suspend fun createUser(user: User) {
        withContext(Dispatchers.IO) {
            userDao.insertUser(user.toRoomUser(user.photo?.let {
                Base64.decode(user.photo, Base64.DEFAULT)
            }?: ByteArray(0)))
        }
    }


}