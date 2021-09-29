package com.haanbin.todayrecode.data.datasource

import com.haanbin.todayrecode.data.local.entity.Recode
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun getRecode(id: Long): Flow<Recode>

    suspend fun getRecentRecode(): Flow<Recode>

    suspend fun getRecodes(isAsc: Boolean, start: Int, size: Int): Flow<List<Recode>>

    suspend fun getTodayRecode(): Flow<Recode>

    suspend fun insertRecode(recode: Recode)

    suspend fun updateRecode(recode: Recode)

    suspend fun deleteRecode(recode: Recode)
}