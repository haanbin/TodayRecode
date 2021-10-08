package com.haanbin.todayrecode.data

import com.haanbin.todayrecode.data.datasource.LocalDataSource
import com.haanbin.todayrecode.data.local.entity.Recode
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) :
    Repository {

    override suspend fun getRecode(id: Long): Flow<Recode> = localDataSource.getRecode(id)

    override suspend fun getRecentRecode(): Flow<Recode> = localDataSource.getRecentRecode()

    override suspend fun getRecodes(isAsc: Boolean, start: Date): Flow<List<Recode>> =
        localDataSource.getRecodes(isAsc, start)

    override suspend fun getTodayRecode(): Flow<Recode?> = localDataSource.getTodayRecode()

    override suspend fun insertOrUpdateRecode(recode: Recode) =
        localDataSource.insertOrUpdateRecode(recode)

    override suspend fun deleteRecode(recode: Recode) = localDataSource.deleteRecode(recode)
}