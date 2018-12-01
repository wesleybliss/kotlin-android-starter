package com.kotlinandroidstarter.app

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.facebook.stetho.Stetho
import com.kotlinandroidstarter.app.api.ApiService
import com.kotlinandroidstarter.app.di.*
import com.orhanobut.hawk.Hawk
import com.squareup.leakcanary.LeakCanary
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import io.fabric.sdk.android.Fabric
import io.realm.Realm
import io.realm.RealmConfiguration
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.android.startKoin
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.regex.Pattern

class App : Application() {
    
    companion object {
        
        lateinit var instance: App
            private set
        
    }
    
    override fun onCreate() {
        
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this))
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        
        instance = this
        
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
        
        // Shared preferences helper
        Hawk.init(this).build()
        
        initRealm()
        initLogger()

        startKoin(this, listOf(
            AppModule.module,
            ApiModule.module,
            ViewModule.module,
            RepositoryModule.module
            /*interactorsModule,
            mappersModule,
            networkModule*/
        ))
        
    }
    
    private fun initRealm() {
        
        Realm.init(this)
        
        val realmConfig = RealmConfiguration.Builder()
            .name("kotlin_android_starter.realm")
        
        // If we're in debug, just nuke the DB if a migration is needed
        if (BuildConfig.DEBUG)
            realmConfig.deleteRealmIfMigrationNeeded()
        
        Realm.setDefaultConfiguration(realmConfig.build())
        
        if (BuildConfig.DEBUG) {
            
            LeakCanary.install(this)
            //Stetho.initializeWithDefaults(this)
            
            val realmInspector = RealmInspectorModulesProvider.builder(this)
                .withFolder(cacheDir)
                //.withEncryptionKey("encrypted.realm", key)
                .withMetaTables()
                .withDescendingOrder()
                .withLimit(1000)
                .databaseNamePattern(Pattern.compile(".+\\.realm"))
                .build()
            
            // @todo https://www.littlerobots.nl/blog/stetho-for-android-debug-builds-only/
            Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(realmInspector)
                    .build())

        }
        
    }

    private fun initLogger() {
        
        //val fabric = Fabric.Builder(this)
        
        if (BuildConfig.DEBUG) {
            //fabric.kits(Crashlytics()).debuggable(true)
            Timber.plant(Timber.DebugTree())
        }
        /*else {
            Timber.plant(CrashlyticsTimberTree())
        }*/
        
        //Fabric.with(fabric.build())
        
    }
    
}
