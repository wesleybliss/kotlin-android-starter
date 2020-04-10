package com.gammagamma.di

import com.gammagamma.di.modules.*
import com.gammagamma.kotlinandroidstarter.network.modules.CoilModule
import com.gammagamma.kotlinandroidstarter.network.modules.MoshiModule
import com.gammagamma.kotlinandroidstarter.network.modules.OkHttpModule
import com.gammagamma.kotlinandroidstarter.network.modules.RetrofitModule

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
