package com.mx.kavak.android.gnomegame.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mx.kavak.android.gnomegame.data.database.entities.InhabitantFriends
import com.mx.kavak.android.gnomegame.data.database.entities.InhabitantProfession

@Dao
interface InhabitantFriendsDao {

    @Query("SELECT * FROM InhabitantFriends WHERE idInhabitant = :inhabitantId")
    fun findByInhabitantId(inhabitantId: Int): List<InhabitantFriends?>

    @Query("SELECT InhabitantFriends.friend_name FROM InhabitantFriends WHERE idInhabitant = :inhabitantId")
    fun findByInhabitantName(inhabitantId: Int): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInhabitantFriends(inhabitantFriends: List<InhabitantFriends>)

}