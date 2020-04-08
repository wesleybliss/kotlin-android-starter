package com.gammagamma.kotlinandroidstarter.network.modules

import android.annotation.SuppressLint
import com.gammagamma.kotlinandroidstarter.network.BuildConfig
import com.gammagamma.logging.plankW
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

val OkHttpModule = module {
    single { buildOkHttpClientBuilder() }
    single { buildOkHttpClient(get()) }
}

private fun buildOkHttpClientBuilder() : OkHttpClient.Builder =
    OkHttpClient.Builder().apply {
        
        readTimeout(BuildConfig.API_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        writeTimeout(BuildConfig.API_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        connectTimeout(BuildConfig.API_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        
        if (BuildConfig.DEBUG) {
            // Enable logging
            val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
            @Suppress("ConstantConditionIf")
            val level =
                if (BuildConfig.HTTP_LOGGING_ENABLED)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
            httpLoggingInterceptor.level = level
            addInterceptor(httpLoggingInterceptor)
        }
        
        if (BuildConfig.DEBUG /* @todo & override SSL in debug? */) {
            
            plankW("Relaxing security on TLS certs")
            
            followSslRedirects(true)
            
            // Trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> = arrayOf()
                @SuppressLint("TrustAllX509TrustManager")
                override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}
                @SuppressLint("TrustAllX509TrustManager")
                override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}
            })
            
            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            
            // Socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory
            
            sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            hostnameVerifier(HostnameVerifier { _, _ -> true })
            
        }
        
        // OAuth2 token bearer header
        //addInterceptor(AuthInterceptor())
        
    }

private fun buildOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient =
    builder.build()
