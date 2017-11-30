package com.kotlinandroidstarter.app.di

import com.kotlinandroidstarter.app.repos.ApiRepo
import com.kotlinandroidstarter.app.services.ApiService
import dagger.Module
import javax.inject.Inject
import javax.inject.Singleton

@Module
@Singleton
abstract class ApiRepoModule {
    
    @Inject lateinit var apiService: ApiService
    
    fun provideApiRepo() = ApiRepo(apiService)
    
}