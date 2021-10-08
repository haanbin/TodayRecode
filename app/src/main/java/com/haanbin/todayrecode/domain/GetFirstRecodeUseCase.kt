package com.haanbin.todayrecode.domain

import com.haanbin.todayrecode.data.RepositoryImpl
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetFirstRecodeUseCase @Inject constructor(private val repository: RepositoryImpl) {

    suspend operator fun invoke() = repository.getFirstRecode()
}