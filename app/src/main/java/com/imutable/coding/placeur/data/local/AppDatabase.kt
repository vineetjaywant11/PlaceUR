package com.imutable.coding.placeur.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.imutable.coding.placeur.data.local.dao.CategoryDao
import com.imutable.coding.placeur.data.local.dao.PlaceDao
import com.imutable.coding.placeur.data.local.dao.UserDao
import com.imutable.coding.placeur.model.Category
import com.imutable.coding.placeur.model.Place
import com.imutable.coding.placeur.model.User

@Database(
    entities = [Place::class, User::class, Category::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPlaceDao(): PlaceDao
    abstract fun getUserDao(): UserDao
    abstract fun getCategory(): CategoryDao

    companion object {
        private const val DB_NAME = "placeur_database"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}