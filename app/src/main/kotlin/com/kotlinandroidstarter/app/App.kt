package com.kotlinandroidstarter.app

import android.app.Application
import com.gammagamma.logging.Plank
import com.gammagamma.di.ModuleProvider
import com.orhanobut.hawk.Hawk
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
        Hawk.init(this).build()
        
        startKoin {
            logger(PrintLogger(Level.INFO))
            androidContext(instance)
            modules(ModuleProvider.modules)
        }
        
    }
    
}
