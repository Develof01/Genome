package com.mx.kavak.android.gnomegame.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Inhabitant(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "age")
    val age: Int,
    @ColumnInfo(name = "hair_color")
    val hair_color: String,
    @ColumnInfo(name = "height")
    val height: Double,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,
    @ColumnInfo(name = "weight")
    val weight: Double,
    @ColumnInfo(name = "photo")
    val photo: Int,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean
)