package com.haanbin.todayrecode.data.datasource

import com.haanbin.todayrecode.data.local.entity.Recode
import com.haanbin.todayrecode.data.local.dao.RecodeDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSourceImpl @Inject constructor(private val recodeDao: RecodeDao) : LocalDataSource {

    override suspend fun getRecode(id: Long): Flow<Recode> = recodeDao.selectRecode(id)

    override suspend fun getRecentRecode(): Flow<Recode> = recodeDao.selectRecentRecode()

    override suspend fun getRecodes(isAsc: Boolean, start: Int, size: Int): Flow<List<Recode>> =
        recodeDao.selectRecodes(isAsc, start, size)

    override suspend fun getTodayRecode(): Flow<Recode> = recodeDao.selectTodayRecode()

    override suspend fun insertRecode(recode: Recode) = recodeDao.insert(recode)

    override suspend fun updateRecode(recode: Recode) = recodeDao.update(recode)

    override suspend fun deleteRecode(recode: Recode) = recodeDao.delete(recode)
}