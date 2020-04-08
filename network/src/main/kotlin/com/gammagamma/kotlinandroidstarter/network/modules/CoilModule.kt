package com.gammagamma.kotlinandroidstarter.network.modules

import android.content.Context
import coil.ImageLoader
import coil.util.CoilUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

val CoilModule  = module {
    
    single { buildDefaultImageLoader(get(), get()) }
    
}

private fun buildDefaultImageLoader(
    applicationContext: Context,
    okHttpClientBuilder: OkHttpClient.Builder) : ImageLoader =
    ImageLoader(applicationContext) {
        
        // Use 50% of the application's available memory
        availableMemoryPercentage(0.5)
        
        // Use 50% of the memory allocated to this ImageLoader for the bitmap pool
        bitmapPoolPercentage(0.5)
        
        // Show a short crossfade when loading images from network or disk into an ImageView
        crossfade(true)
        
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        
        okHttpClient {
            // Lazily create the OkHttpClient that is used for network operations.
            okHttpClientBuilder
                .cache(CoilUtils.createDefaultCache(applicationContext))
                .addInterceptor(httpLoggingInterceptor)
                .build()
        }
        
    }
