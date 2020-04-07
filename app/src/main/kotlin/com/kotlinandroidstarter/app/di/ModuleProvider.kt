package com.kotlinandroidstarter.app.di

import com.gammagamma.kotlinandroidstarter.net.modules.CoilModule
import com.gammagamma.kotlinandroidstarter.net.modules.MoshiModule
import com.gammagamma.kotlinandroidstarter.net.modules.OkHttpModule
import com.gammagamma.kotlinandroidstarter.net.modules.RetrofitModule
import com.kotlinandroidstarter.app.di.modules.*
import org.koin.core.module.Module

object ModuleProvider {
    
    val modules = mutableListOf<Module>().apply {
        add(AppModule)
        /*addAll(DomainModules.modules)
        addAll(NetworkModules.modules)*/
        //addAll(PersistenceModules.modules)
        add(OkHttpModule)
        add(RetrofitModule)
        add(CoilModule)
        add(MoshiModule)
        add(UIModule)
        add(MainModule)
        add(AboutModule)
        add(HomeModule)
        add(FeedModule)
    }
    
}
