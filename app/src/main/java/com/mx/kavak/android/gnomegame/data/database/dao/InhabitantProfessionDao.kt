package com.mx.kavak.android.gnomegame.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mx.kavak.android.gnomegame.data.database.entities.InhabitantProfession

@Dao
interface InhabitantProfessionDao {

    @Query("SELECT * FROM InhabitantProfession WHERE idInhabitant = :inhabitantId")
    fun findByInhabitantId(inhabitantId: Int): List<InhabitantProfession?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInhabitantProfessions(inhabitantProfessions: List<InhabitantProfession>)

}