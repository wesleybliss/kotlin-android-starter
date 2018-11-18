package com.kotlinandroidstarter.app

import android.app.Application
import com.kotlinandroidstarter.app.api.ApiService
import com.kotlinandroidstarter.app.di.apiModule
import com.kotlinandroidstarter.app.di.appModule
import com.kotlinandroidstarter.app.di.repositoryModule
import com.kotlinandroidstarter.app.di.viewModule
import com.orhanobut.hawk.Hawk
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.android.startKoin
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
        
        lateinit var apiService: ApiService
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

        startKoin(this, listOf(
            appModule,
            apiModule,
            viewModule,
            repositoryModule
            /*interactorsModule,
            mappersModule,
            networkModule*/
        ))
        
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
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient)
            .build()
        
        
        apiService = retrofit.create(ApiService::class.java)
        
    }
    
}
