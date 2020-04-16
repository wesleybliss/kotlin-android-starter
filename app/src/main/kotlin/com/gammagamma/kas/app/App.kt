package com.gammagamma.kas.app

import android.app.Application
import com.gammagamma.kas.di.ModuleProvider
import com.gammagamma.kas.domain.storage.IStorageProvider
import com.gammagamma.kas.logging.Plank
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
        //registerActivityLifecycleCallbacks(getKoin().get<Provider<Activity>>().callbacks)
        
    }
    
}
