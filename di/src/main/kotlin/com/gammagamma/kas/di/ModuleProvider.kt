package com.gammagamma.kas.di

import android.app.Application
import com.gammagamma.kas.di.module.*
import com.gammagamma.kas.network.module.*
import com.gammagamma.kas.ui.observable.ConnectivityLiveData.Companion.instance
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.PrintLogger

@InternalCoroutinesApi
object ModuleProvider {
    
    val modules = mutableListOf(
        AppModule,
        OkHttpModule,
        RetrofitModule,
        CoilModule,
        MoshiModule,
        NetworkModule,
        DatabaseModule,
        NavigationModule,
        StorageModule,
        UIModule,
        MainModule,
        AboutModule,
        HomeModule,
        FeedModule,
        KitchenSinkModule
    )
    
    fun initKoin(app: Application) {
        
        if (BuildConfig.DEBUG)
            modules.add(DebugOkHttpModule)
        
        startKoin {
            logger(PrintLogger(Level.INFO))
            androidContext(app)
            modules(modules)
        }
        
    }
    
}
