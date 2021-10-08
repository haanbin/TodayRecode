package com.haanbin.todayrecode.domain

import com.haanbin.todayrecode.data.Repository
import com.haanbin.todayrecode.ext.toCurrentDate
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetRecodesUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(isAsc: Boolean, start: Date = Date().toCurrentDate()) =
        repository.getRecodes(isAsc, start)
}