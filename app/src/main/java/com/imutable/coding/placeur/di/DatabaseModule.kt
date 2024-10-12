package com.imutable.coding.placeur.di


import android.app.Application
import com.imutable.coding.placeur.data.local.AppDatabase
import com.imutable.coding.placeur.data.preference.MyPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = AppDatabase.getInstance(application)

    @Singleton
    @Provides
    fun providePlaceDao(database: AppDatabase) = database.getPlaceDao()

    @Singleton
    @Provides
    fun provideUserDao(database: AppDatabase) = database.getUserDao()

    @Singleton
    @Provides
    fun provideCategoryDao(database: AppDatabase) = database.getCategory()

    @Singleton
    @Provides
    fun provideMyPreference(application: Application) = MyPreference(application)




}