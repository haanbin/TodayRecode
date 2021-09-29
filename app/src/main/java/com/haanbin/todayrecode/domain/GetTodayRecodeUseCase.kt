package com.haanbin.todayrecode.domain

import com.haanbin.todayrecode.data.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTodayRecodeUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke() = repository.getTodayRecode()
}