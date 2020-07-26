package com.mx.kavak.android.data.sources.local

import com.mx.kavak.android.domain.models.User

interface UserLocalDataSource {

    suspend fun getLocalUser() : User?
    suspend fun createUser(user: User)

}