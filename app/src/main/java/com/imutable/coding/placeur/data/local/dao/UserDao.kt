package com.imutable.coding.placeur.data.local.dao

import androidx.room.*
import com.imutable.coding.placeur.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

//    @Query("SELECT * FROM ${User.TABLE_NAME}")
//    fun getAllUsers(): Flow<List<User>>

    @Update
    suspend fun updateUser(user: User)

}