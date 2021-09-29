package com.haanbin.todayrecode.data.local.dao

import androidx.room.*
import com.haanbin.todayrecode.data.local.entity.Recode
import kotlinx.coroutines.flow.Flow

@Dao
interface RecodeDao : BaseDao<Recode> {

    @Query("SELECT * FROM recode WHERE id = :id")
    fun selectRecode(id: Long): Flow<Recode>

    @Query("SELECT * FROM recode WHERE inputDate BETWEEN date('now') AND date('now', '+1 day') LIMIT 1")
    fun selectTodayRecode(): Flow<Recode>

    @Query("SELECT * FROM recode ORDER BY inputDate DESC LIMIT 1")
    fun selectRecentRecode(): Flow<Recode>

    @Query("SELECT * FROM recode ORDER BY CASE WHEN :isAsc = 1 THEN inputDate END ASC, CASE WHEN :isAsc = 0 THEN inputDate END DESC LIMIT :size OFFSET :start")
    fun selectRecodes(isAsc: Boolean, start: Int, size: Int): Flow<List<Recode>>
}