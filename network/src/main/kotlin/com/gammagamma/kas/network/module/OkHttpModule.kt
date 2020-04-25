package com.gammagamma.kas.network.module

import com.gammagamma.kas.network.BuildConfig
import okhttp3.OkHttpClient
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

val OkHttpModule = module {
    single { buildOkHttpClientBuilder() }
    single { buildOkHttpClient(get()) }
}

private fun buildOkHttpClientBuilder() = OkHttpClient.Builder().apply {
    
    readTimeout(BuildConfig.API_TIMEOUT_SECONDS, TimeUnit.SECONDS)
    writeTimeout(BuildConfig.API_TIMEOUT_SECONDS, TimeUnit.SECONDS)
    connectTimeout(BuildConfig.API_TIMEOUT_SECONDS, TimeUnit.SECONDS)
    
    // For debug configuration, see
    // src/debug/.../DebugOkHttpModule.kt in this module
    
    // OAuth2 token bearer header
    //addInterceptor(AuthInterceptor())
    
}

private fun buildOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient =
    builder.build()
