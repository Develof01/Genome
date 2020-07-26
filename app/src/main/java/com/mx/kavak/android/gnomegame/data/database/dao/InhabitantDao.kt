package com.mx.kavak.android.gnomegame.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mx.kavak.android.gnomegame.data.database.entities.Inhabitant

@Dao
interface InhabitantDao {

    @Query("SELECT * FROM Inhabitant WHERE id = :inhabitantId")
    fun findById(inhabitantId: Int): Inhabitant?

    @Query("SELECT * FROM Inhabitant WHERE name LIKE '%' || :name || '%'")
    fun findAllByName(name: String): LiveData<List<Inhabitant>>

    @Query("SELECT * FROM Inhabitant WHERE name in (:names)")
    fun findAllByNames(names: List<String>): List<Inhabitant>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertInhabitants(inhabitants: List<Inhabitant>)

    @Query("UPDATE Inhabitant SET isFavorite = :isFavorite  WHERE id = :idInhabitant")
    fun updateFavoriteStatus(idInhabitant: Int, isFavorite: Boolean)

}