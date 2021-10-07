package com.haanbin.todayrecode.domain

import com.haanbin.todayrecode.data.RepositoryImpl
import com.haanbin.todayrecode.data.local.entity.Recode
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteRecodeUseCase @Inject constructor(private val repository: RepositoryImpl) {

    suspend operator fun invoke(recode: Recode) {
        repository.deleteRecode(recode)
    }
}