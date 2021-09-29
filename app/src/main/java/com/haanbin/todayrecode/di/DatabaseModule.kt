package com.haanbin.todayrecode.di

import android.content.Context
import androidx.room.Room
import com.haanbin.todayrecode.data.local.AppDatabase
import com.haanbin.todayrecode.data.local.dao.RecodeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    const val DB_NAME = "todayRecode"

    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            DB_NAME
        ).build()

    @Provides
    fun provideRecodeDao(database: AppDatabase): RecodeDao = database.recodeDao()
}
