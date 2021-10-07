package com.haanbin.todayrecode

import com.haanbin.todayrecode.data.datasource.LocalDataSource
import com.haanbin.todayrecode.data.datasource.LocalDataSourceImpl
import com.haanbin.todayrecode.data.local.entity.Recode
import com.haanbin.todayrecode.data.local.dao.RecodeDao
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.*

class LocalDataSourceTest {

    private val recodeDao: RecodeDao = mockk(relaxed = true)
    private lateinit var localDataSource: LocalDataSource

    @BeforeEach
    fun setup() {
        localDataSource = LocalDataSourceImpl(recodeDao)
    }

    @Test
    @DisplayName("기록 입력")
    fun `기록 입력`() = runBlocking {
        val recode = Recode(CONTENT, Date())
        localDataSource.insertRecode(recode)
        coVerify {
            recodeDao.insertOrUpdate(recode)
        }
    }

    @Test
    @DisplayName("최근 기록 조회")
    fun `최근 기록 조회`() = runBlocking {
        localDataSource.getRecentRecode()
        coVerify {
            recodeDao.selectRecentRecode()
        }
    }

    companion object {

        const val CONTENT = "content"
    }
}