package com.haanbin.todayrecode.data

import com.haanbin.todayrecode.data.local.entity.Recode
import com.haanbin.todayrecode.ext.toCurrentDate
import kotlinx.coroutines.flow.Flow
import java.util.*

interface Repository {

    suspend fun getRecode(id: Long): Flow<Recode>

    suspend fun getRecentRecode(): Flow<Recode>

    suspend fun getRecodes(isAsc: Boolean, start: Date = Date().toCurrentDate()): Flow<List<Recode>>

    suspend fun getTodayRecode(): Flow<Recode?>

    suspend fun insertOrUpdateRecode(recode: Recode)

    suspend fun deleteRecode(recode: Recode)

    suspend fun getFirstRecode(): Flow<Recode?>
}