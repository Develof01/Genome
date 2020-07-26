package com.mx.kavak.android.gnomegame.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mx.kavak.android.gnomegame.data.database.dao.*
import com.mx.kavak.android.gnomegame.data.database.entities.*
import com.mx.kavak.android.gnomegame.utils.Constant

@Database(
    entities = [
        User::class,
        Inhabitant::class,
        InhabitantProfession::class,
        InhabitantFriends::class,
        Notification::class],
    version = Constant.DB_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            Constant.DB_NAME
        ).build()

        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        Constant.DB_NAME
                    ).build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun userDao(): UserDao
    abstract fun inhabitantDao(): InhabitantDao
    abstract fun inhabitantProfessionDao(): InhabitantProfessionDao
    abstract fun inhabitantFriendsDao(): InhabitantFriendsDao
    abstract fun notificationDao(): NotificationDao

}