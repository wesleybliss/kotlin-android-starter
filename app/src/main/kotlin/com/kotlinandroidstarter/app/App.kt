package com.kotlinandroidstarter.app

import android.app.Application
import com.kotlinandroidstarter.app.api.Api
import com.orhanobut.hawk.Hawk
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

class App : Application() {
    
    companion object {
        
        lateinit var instance: App
            private set
        
        lateinit var okHttpClient: OkHttpClient
            private set
    
        lateinit var retrofit: Retrofit
            private set
        
        lateinit var api: Api
            private set
        
    }
    
    override fun onCreate() {
    
        super.onCreate()
    
        instance = this
        
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
        
        // Shared preferences helper
        Hawk.init(this).build()
        
        setupRetrofit()
        
    }
    
    private fun setupRetrofit() {
        
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        
        okHttpClient = OkHttpClient.Builder()
            /*.addInterceptor(ApiHeadersInterceptor())*/
            .addInterceptor(loggingInterceptor)
            /*.addInterceptor(LogRequestsInterceptor())*/
            .build()
        
        retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(Api.BASE_URL)
            .client(okHttpClient)
            .build()
        
        
        api = retrofit.create(Api::class.java)
        
    }
    
}
