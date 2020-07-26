package com.mx.kavak.android.domain.models

import java.io.Serializable


data class Inhabitant(
    val age: Int,
    var friends: List<String>,
    val hair_color: String,
    val height: Double,
    val id: Int,
    val name: String,
    var professions: List<String>,
    val thumbnail: String,
    val weight: Double,
    val photo: Int,
    val isFavorite: Boolean
): Serializable