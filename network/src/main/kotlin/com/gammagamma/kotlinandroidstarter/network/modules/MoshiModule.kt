package com.gammagamma.kotlinandroidstarter.network.modules

import com.gammagamma.kotlinandroidstarter.network.adapter.DateTimeAdapter
import com.squareup.moshi.Moshi
import org.koin.dsl.module

val MoshiModule = module { 
    
    single {
        Moshi.Builder()
            .add(DateTimeAdapter())
            .build()
    }
    
}
