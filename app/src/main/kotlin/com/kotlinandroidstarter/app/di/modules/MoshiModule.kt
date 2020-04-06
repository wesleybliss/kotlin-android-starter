package com.kotlinandroidstarter.app.di.modules

import com.squareup.moshi.Moshi
import org.koin.dsl.module

val MoshiModule = module { 
    
    single { Moshi.Builder().build() }
    
}
