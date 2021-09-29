package com.haanbin.todayrecode

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.haanbin.todayrecode.data.local.AppDatabase
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

open class DatabaseTest {

    protected lateinit var database: AppDatabase

    @BeforeEach
    open fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .build()
    }

    @AfterEach
    fun tearDown() {
        database.close()
    }
}