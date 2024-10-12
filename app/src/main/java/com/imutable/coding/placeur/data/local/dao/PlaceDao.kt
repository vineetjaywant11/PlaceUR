package com.imutable.coding.placeur.data.local.dao

import androidx.room.*
import com.imutable.coding.placeur.model.Place
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlace(place: Place)

    @Query("SELECT * FROM ${Place.TABLE_NAME}")
    fun getAllPlace(): Flow<List<Place>>

    @Update
    suspend fun updatePlace(place: Place)

}