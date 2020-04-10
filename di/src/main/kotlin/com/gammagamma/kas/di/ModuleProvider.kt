package com.gammagamma.kas.di

import com.gammagamma.kas.di.modules.*
import com.gammagamma.kas.network.modules.CoilModule
import com.gammagamma.kas.network.modules.MoshiModule
import com.gammagamma.kas.network.modules.OkHttpModule
import com.gammagamma.kas.network.modules.RetrofitModule

object ModuleProvider {
    
    val modules = listOf(
        AppModule,
        OkHttpModule,
        RetrofitModule,
        CoilModule,
        MoshiModule,
        StorageModule,
        UIModule,
        MainModule,
        AboutModule,
        HomeModule,
        FeedModule
    )
    
}
