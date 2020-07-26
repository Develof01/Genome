package com.mx.kavak.android.data.repository

import com.mx.kavak.android.data.sources.local.UserLocalDataSource
import com.mx.kavak.android.domain.models.User

class UserRepository(private val localDataSource: UserLocalDataSource) {

    suspend fun getLocalUser(): User? =
        localDataSource.getLocalUser()

    suspend fun createUser(user: User) {
        localDataSource.createUser(user)
    }


}