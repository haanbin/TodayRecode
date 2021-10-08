package com.haanbin.todayrecode.data.datasource

import com.haanbin.todayrecode.data.local.entity.Recode
import com.haanbin.todayrecode.data.local.dao.RecodeDao
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSourceImpl @Inject constructor(private val recodeDao: RecodeDao) : LocalDataSource {

    override suspend fun getRecode(id: Long): Flow<Recode> = recodeDao.selectRecode(id)

    override suspend fun getRecentRecode(): Flow<Recode> = recodeDao.selectRecentRecode()

    override suspend fun getRecodes(isAsc: Boolean, start: Date): Flow<List<Recode>> =
        recodeDao.selectRecodes(isAsc, start)

    override suspend fun getTodayRecode(): Flow<Recode> = recodeDao.selectTodayRecode()

    override suspend fun insertOrUpdateRecode(recode: Recode) = recodeDao.insertOrUpdate(recode)

    override suspend fun deleteRecode(recode: Recode) = recodeDao.delete(recode)
}