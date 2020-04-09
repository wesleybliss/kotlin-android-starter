package com.kotlinandroidstarter.app

import android.app.Application
import com.gammagamma.di.ModuleProvider
import com.gammagamma.kotlinandroidstarter.domain.storage.IStorageProvider
import com.gammagamma.logging.Plank
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.PrintLogger

class App : Application() {
    
    companion object {
        
        lateinit var instance: App
            private set
        
    }
    
    override fun onCreate() {
    
        super.onCreate()
    
        instance = this
        
        Plank.init()
        
        startKoin {
            logger(PrintLogger(Level.INFO))
            androidContext(instance)
            modules(ModuleProvider.modules)
        }
        
        getKoin().get<IStorageProvider>().init()
        
    }
    
}
