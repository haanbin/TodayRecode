package com.haanbin.todayrecode.data.local.dao

import androidx.room.*
import com.haanbin.todayrecode.data.local.entity.Recode
import com.haanbin.todayrecode.ext.toCurrentDate
import com.haanbin.todayrecode.ext.toTomorrowDate
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface RecodeDao : BaseDao<Recode> {

    @Query("SELECT * FROM recode WHERE id = :id")
    fun selectRecode(id: Long): Flow<Recode>

    @Query("SELECT * FROM recode WHERE inputDate BETWEEN :start AND :end LIMIT 1")
    fun selectTodayRecode(
        start: Date = Date().toCurrentDate(),
        end: Date = Calendar.getInstance().toTomorrowDate()
    ): Flow<Recode>

    @Query("SELECT * FROM recode ORDER BY inputDate DESC LIMIT 1")
    fun selectRecentRecode(): Flow<Recode>

    @Query("SELECT * FROM recode WHERE inputDate <= :start ORDER BY CASE WHEN :isAsc = 1 THEN inputDate END ASC, CASE WHEN :isAsc = 0 THEN inputDate END DESC")
    fun selectRecodes(isAsc: Boolean, start: Date = Date().toCurrentDate()): Flow<List<Recode>>
}