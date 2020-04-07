package com.gammagamma.kotlinandroidstarter.net.modules

import com.gammagamma.kotlinandroidstarter.net.adapter.DateTimeAdapter
import com.squareup.moshi.Moshi
import org.koin.dsl.module

val MoshiModule = module { 
    
    single {
        Moshi.Builder()
            .add(DateTimeAdapter())
            .build()
    }
    
}
