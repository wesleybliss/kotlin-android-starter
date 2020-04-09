package com.gammagamma.di

import com.gammagamma.kotlinandroidstarter.network.modules.CoilModule
import com.gammagamma.kotlinandroidstarter.network.modules.MoshiModule
import com.gammagamma.kotlinandroidstarter.network.modules.OkHttpModule
import com.gammagamma.kotlinandroidstarter.network.modules.RetrofitModule
import com.gammagamma.di.modules.*
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
