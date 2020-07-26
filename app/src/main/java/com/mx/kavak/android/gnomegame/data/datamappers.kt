package com.mx.kavak.android.gnomegame.data

import android.util.Base64
import com.mx.kavak.android.domain.models.Inhabitant
import com.mx.kavak.android.domain.models.Notification
import com.mx.kavak.android.domain.models.User
import com.mx.kavak.android.gnomegame.data.database.entities.User as RoomUser
import com.mx.kavak.android.gnomegame.data.database.entities.Inhabitant as RoomInhabitant
import com.mx.kavak.android.gnomegame.data.database.entities.InhabitantFriends as RoomFriends
import com.mx.kavak.android.gnomegame.data.database.entities.InhabitantProfession as RoomProfessions
import com.mx.kavak.android.gnomegame.data.database.entities.Notification as RoomNotification

fun RoomUser.toDomainUser(): User? =
    User(id, username, Base64.encodeToString(photo, 0))

fun User.toRoomUser(image: ByteArray?): RoomUser =
    RoomUser(0, username, image)

fun Inhabitant.toRoomInhabitant(): RoomInhabitant =
    RoomInhabitant(id, age, hair_color, height, name, thumbnail, weight, (1..100).random(), false)

fun  RoomInhabitant.toDomainInhabitant(friends: List<String>, professions: List<String>): Inhabitant =
    Inhabitant(age, friends, hair_color, height, id, name, professions, thumbnail, weight, photo, isFavorite)

fun RoomFriends.toDomainInhabitantFriends(): String = friend_name

fun String.toRoomFriends(idInhabitant: Int): RoomFriends =
    RoomFriends(0, idInhabitant, this)

fun RoomProfessions.toDomainInahabitantProgessions(): String = profession

fun String.toRoomProfessions(idInhabitant: Int): RoomProfessions =
    RoomProfessions(0, idInhabitant, this)

fun RoomNotification.toDomainNotification(): Notification =
    Notification(title, body)
