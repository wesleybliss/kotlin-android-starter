package com.gammagamma.kas.network.modules

import com.gammagamma.kas.network.adapter.DateTimeAdapter
import com.squareup.moshi.Moshi
import org.koin.dsl.module

val MoshiModule = module { 
    
    single {
        Moshi.Builder()
            .add(DateTimeAdapter())
            .build()
    }
    
}
