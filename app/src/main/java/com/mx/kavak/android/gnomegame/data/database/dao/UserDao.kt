package com.mx.kavak.android.gnomegame.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mx.kavak.android.gnomegame.data.database.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun getAll(): List<User>

    @Query("SELECT * FROM User WHERE id = :userId")
    fun findById(userId: Int): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

}