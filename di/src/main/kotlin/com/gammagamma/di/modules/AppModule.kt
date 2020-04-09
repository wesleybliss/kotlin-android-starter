package com.gammagamma.di.modules

import com.gammagamma.kotlinandroidstarter.domain.storage.IStorageProvider
import com.gammagamma.kotlinandroidstarter.storage.HawkStorageProvider
import org.koin.dsl.module

val AppModule = module {
    
    single<IStorageProvider> { HawkStorageProvider() }
    
    //single<ITopActivityProvider> { TopActivityProvider() }
    
}
