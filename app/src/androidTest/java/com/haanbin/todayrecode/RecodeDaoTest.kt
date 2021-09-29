package com.haanbin.todayrecode

import com.haanbin.todayrecode.data.local.entity.Recode
import com.haanbin.todayrecode.data.local.dao.RecodeDao
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

class RecodeDaoTest : DatabaseTest() {

    private lateinit var recodeDao: RecodeDao

    @BeforeEach
    override fun setUp() {
        super.setUp()
        recodeDao = database.recodeDao()
    }

    @Test
    fun writeUserAndRead() = runBlocking {
        recodeDao.insert(Recode(CONTENT, Date()))
        recodeDao.selectRecentRecode().collect {
            Assert.assertEquals(it.content, CONTENT)
        }
    }

    companion object {
        private const val CONTENT = "content"
    }
}