package com.kotlinandroidstarter.app.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kotlinandroidstarter.app.BuildConfig
import com.kotlinandroidstarter.app.api.ApiService
import com.kotlinandroidstarter.app.api.LiveDataCallAdapterFactory
import com.kotlinandroidstarter.app.api.MutableLiveDataCallAdapterFactory
import com.kotlinandroidstarter.app.api.RealmListJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitProxy(val retrofit: Retrofit)

object ApiModule {
    
    val module = module {
        single { createOkHttpClient() }
        single { createMoshi() }
        single { createRetrofit(get()) }
        single { createRetrofitProxy(get(), get()) }
        //single { createAuthApiService(get()) }
        single { createApiService(get()) }
    }
    
    private fun createOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().apply {
            
            // OAuth2 token bearer header
            //addInterceptor(AuthInterceptor())
            
            if (BuildConfig.DEBUG) {
                val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(httpLoggingInterceptor)
            }
            
        }.build()
    
    private fun createMoshi() : Moshi = Moshi.Builder()
        .add(RealmListJsonAdapterFactory())
        .build()
    
    // Default Retrofit, without de-enveloping & other middleware
    // This is mostly used for edge-case responses, like authentication,
    // which does not return an eveloped response (just raw JSON)
    private fun createRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .addCallAdapterFactory(MutableLiveDataCallAdapterFactory())
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BuildConfig.API_BASE_URL)
        .build()
    
    // Secondary Retrofit, with a bunch of required middleware
    // Most calls should use this, since the API returns an enveloped response, e.g.:
    // {
    //     "meta": ...(we don't really need),
    //     "data": ...(actual data we need),
    //     "pagination": ...(pagination data)
    // }
    private fun createRetrofitProxy(okHttpClient: OkHttpClient, moshi: Moshi) = RetrofitProxy(
        Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addCallAdapterFactory(MutableLiveDataCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BuildConfig.API_BASE_URL)
            .build()
    )
    
    /*private fun createAuthApiService(retrofit: Retrofit)
        : AuthApiService = retrofit.create(AuthApiService::class.java)*/
    
    private fun createApiService(retrofitProxy: RetrofitProxy)
        : ApiService = retrofitProxy.retrofit.create(ApiService::class.java)
    
}
