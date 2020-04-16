package com.gammagamma.kas.di

import com.gammagamma.kas.di.module.*
import com.gammagamma.kas.network.module.CoilModule
import com.gammagamma.kas.network.module.MoshiModule
import com.gammagamma.kas.network.module.OkHttpModule
import com.gammagamma.kas.network.module.RetrofitModule

object ModuleProvider {
    
    val modules = listOf(
        AppModule,
        OkHttpModule,
        RetrofitModule,
        CoilModule,
        MoshiModule,
        NavigationModule,
        StorageModule,
        UIModule,
        MainModule,
        AboutModule,
        HomeModule,
        FeedModule,
        KitchenSinkModule
    )
    
}
