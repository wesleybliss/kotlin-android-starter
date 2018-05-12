package com.kotlinandroidstarter.app.di

import com.kotlinandroidstarter.app.repos.ApiRepo
import com.kotlinandroidstarter.app.services.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ApiServiceModule {
    
    @Provides
    @Singleton
    fun retrofit(): Retrofit {
        
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        
        val okHttpClient = OkHttpClient.Builder()
            /*.addInterceptor(ApiHeadersInterceptor())*/
            .addInterceptor(loggingInterceptor)
            /*.addInterceptor(LogRequestsInterceptor())*/
            .build()
        
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(ApiService.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        
    }
    
    @Provides
    @Singleton
    fun apiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
    
    @Provides
    @Singleton
    fun apiRepo(apiService: ApiService): ApiRepo =
        ApiRepo(apiService)
    
}