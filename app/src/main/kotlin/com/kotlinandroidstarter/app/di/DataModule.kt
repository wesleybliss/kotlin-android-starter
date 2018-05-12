package com.kotlinandroidstarter.app.di

import com.kotlinandroidstarter.app.models.*
import com.kotlinandroidstarter.app.network.DefaultOnDataMismatchAdapter
import com.kotlinandroidstarter.app.network.FilterNullValuesFromListAdapter
import com.kotlinandroidstarter.app.services.ApiService
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
internal object DataModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideMoshi(): Moshi = Moshi.Builder()
//        .add(KotlinJsonAdapterFactory())
        .add(DefaultOnDataMismatchAdapter.newFactory(User::class.java, null))
        .add(DefaultOnDataMismatchAdapter.newFactory(Post::class.java, null))
        .add(DefaultOnDataMismatchAdapter.newFactory(Geo::class.java, null))
        .add(FilterNullValuesFromListAdapter.newFactory(Company::class.java))
        .add(DefaultOnDataMismatchAdapter.newFactory(Address::class.java, null))
        .add(com.squareup.moshi.kotlin.KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    @JvmStatic
    fun provideOkHttp(): OkHttpClient {
        
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        
        return OkHttpClient.Builder()
            /*.addInterceptor(ApiHeadersInterceptor())*/
            .addInterceptor(loggingInterceptor)
            /*.addInterceptor(LogRequestsInterceptor())*/
            .build()
        
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideRetrofit(oktHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
        .client(oktHttpClient)
        .baseUrl(ApiService.BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Singleton
    @Provides
    @JvmStatic
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

}
