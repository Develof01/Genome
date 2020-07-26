package com.mx.kavak.android.gnomegame.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mx.kavak.android.gnomegame.data.database.entities.Notification

@Dao
interface NotificationDao {

    @Query("SELECT * FROM Notification")
    fun getAll(): List<Notification>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotification(notification: Notification)

}