package com.gammagamma.kas.app

import com.facebook.stetho.Stetho
import kotlinx.coroutines.InternalCoroutinesApi

@Suppress("unused")
@InternalCoroutinesApi
class DebugApp : App() {
    
    override fun onCreate() {
        
        super.onCreate()
        
        initStetho()
        
    }
    
    private fun initStetho() {
        
        val dumpApp = Stetho
            .defaultDumperPluginsProvider(applicationContext)
        
        val webKitInspector = Stetho
            .defaultInspectorModulesProvider(applicationContext)
        
        Stetho.initialize(
            Stetho.newInitializerBuilder(applicationContext)
                .enableDumpapp(dumpApp)
                .enableWebKitInspector(webKitInspector)
                .build())
        
    }
    
}
