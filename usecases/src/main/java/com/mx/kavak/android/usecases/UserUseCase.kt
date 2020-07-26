package com.mx.kavak.android.usecases

import com.mx.kavak.android.data.repository.UserRepository
import com.mx.kavak.android.domain.models.User

class UserUseCase(private val repository: UserRepository) {

    suspend fun getLocalUser(): User? =
        repository.getLocalUser()

    suspend fun createUser(user: User) {
        repository.createUser(user)
    }

}