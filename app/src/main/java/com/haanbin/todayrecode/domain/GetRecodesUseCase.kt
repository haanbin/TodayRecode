package com.haanbin.todayrecode.domain

import com.haanbin.todayrecode.data.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetRecodesUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(isAsc: Boolean, start: Int, size: Int) =
        repository.getRecodes(isAsc, start, size)
}