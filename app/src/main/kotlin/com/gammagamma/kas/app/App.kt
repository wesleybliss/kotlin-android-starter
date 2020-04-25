package com.gammagamma.kas.app

import android.app.Application
import com.gammagamma.kas.di.ModuleProvider
import com.gammagamma.kas.domain.storage.IStorageProvider
import com.gammagamma.kas.logging.Plank
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.android.getKoin

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
        
        ModuleProvider.initKoin(this)
        
        getKoin().get<IStorageProvider>().init()
        // registerActivityLifecycleCallbacks(getKoin().get<Provider<Activity>>().callbacks)
        
    }
    
}
