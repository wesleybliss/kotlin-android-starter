package com.gammagamma.kas.app

import android.app.Application
import androidx.annotation.CallSuper
import com.gammagamma.kas.di.ModuleProvider
import com.gammagamma.kas.domain.storage.IStorageProvider
import com.gammagamma.kas.logging.Plank
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.PrintLogger

@Suppress("unused")
@InternalCoroutinesApi
open class App : Application() {
    
    companion object {
        
        lateinit var instance: App
            private set
        
    }
    
    override fun onCreate() {
        
        super.onCreate()
    
        instance = this
        
        Plank.init()
        
        initKoin()
        
        getKoin().get<IStorageProvider>().init()
        // registerActivityLifecycleCallbacks(getKoin().get<Provider<Activity>>().callbacks)
        
    }
    
    protected open fun initKoin() {
        
        startKoin {
            logger(PrintLogger(Level.INFO))
            androidContext(instance)
            modules(ModuleProvider.modules)
        }
        
    }
    
}
