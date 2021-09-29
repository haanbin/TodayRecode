package com.haanbin.todayrecode.di

import com.haanbin.todayrecode.data.Repository
import com.haanbin.todayrecode.data.RepositoryImpl
import com.haanbin.todayrecode.data.datasource.LocalDataSource
import com.haanbin.todayrecode.data.datasource.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {

    @Binds
    abstract fun bindRepository(repository: RepositoryImpl): Repository

    @Binds
    abstract fun bindLocalDataSource(localDataSource: LocalDataSourceImpl): LocalDataSource
}