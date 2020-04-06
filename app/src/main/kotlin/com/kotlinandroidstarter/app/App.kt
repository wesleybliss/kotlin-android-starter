package com.kotlinandroidstarter.app

import android.app.Application
import com.kotlinandroidstarter.app.api.ApiService
import com.kotlinandroidstarter.app.di.ModuleProvider
import com.kotlinandroidstarter.app.di.apiModule
import com.kotlinandroidstarter.app.di.modules.AppModule
import com.orhanobut.hawk.Hawk
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.PrintLogger
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

class App : Application() {
    
    companion object {
        
        lateinit var instance: App
            private set
        
        // @todo move all this shit to koin
        
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

        startKoin {
            logger(PrintLogger(Level.INFO))
            androidContext(instance)
            modules(ModuleProvider.modules)
        }
        
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
