package com.mx.kavak.android.gnomegame.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "photo", typeAffinity = ColumnInfo.BLOB)
    var photo: ByteArray? = null
)