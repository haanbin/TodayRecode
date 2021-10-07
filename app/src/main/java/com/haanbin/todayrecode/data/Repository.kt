package com.haanbin.todayrecode.data

import com.haanbin.todayrecode.data.local.entity.Recode
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getRecode(id: Long): Flow<Recode>

    suspend fun getRecentRecode(): Flow<Recode>

    suspend fun getRecodes(isAsc: Boolean, start: Int, size: Int): Flow<List<Recode>>

    suspend fun getTodayRecode(): Flow<Recode?>

    suspend fun insertOrUpdateRecode(recode: Recode)

    suspend fun deleteRecode(recode: Recode)
}