package com.gammagamma.kas.network.module

import com.gammagamma.kas.network.adapter.AddressIdAdapter
import com.gammagamma.kas.network.adapter.DateTimeAdapter
import com.gammagamma.kas.network.adapter.UserIdAdapter
import com.squareup.moshi.Moshi
import org.koin.dsl.module

val MoshiModule = module { 
    
    single {
        Moshi.Builder()
            .add(DateTimeAdapter())
            .add(UserIdAdapter())
            .add(AddressIdAdapter())
            .build()
    }
    
}
