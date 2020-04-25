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
    
    @Suppress("RedundantOverride")
    override fun initKoin() {
        
        // @todo Remove once using actual Koin overrides
        super.initKoin()
        
        /*startKoin(
            this,
            koinModules.toMutableList().apply {
                add(DebugApiModule.module)
            }
        )*/
        
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
