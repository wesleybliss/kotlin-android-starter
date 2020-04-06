package com.kotlinandroidstarter.app.di

import com.kotlinandroidstarter.app.di.modules.*
import org.koin.core.module.Module

object ModuleProvider {
    
    val modules = mutableListOf<Module>().apply {
        add(AppModule)
        /*addAll(DomainModules.modules)
        addAll(NetworkModules.modules)*/
        //addAll(PersistenceModules.modules)
        add(OkHttpModule.module)
        add(MoshiModule)
        add(UIModule)
        add(MainModule)
        add(AboutModule)
        add(HomeModule)
        add(FeedModule)
    }
    
}
