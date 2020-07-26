package com.mx.kavak.android.gnomegame.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Inhabitant::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("idInhabitant"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class InhabitantFriends (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "idInhabitant")
    val idInhabitant: Int,
    @ColumnInfo(name = "friend_name")
    val friend_name: String
)