package com.haanbin.todayrecode.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.haanbin.todayrecode.data.local.entity.Recode
import com.haanbin.todayrecode.data.local.converter.DateConverters
import com.haanbin.todayrecode.data.local.dao.RecodeDao

@Database(
    entities = [Recode::class],
    version = 1
)
@TypeConverters(DateConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recodeDao(): RecodeDao
}