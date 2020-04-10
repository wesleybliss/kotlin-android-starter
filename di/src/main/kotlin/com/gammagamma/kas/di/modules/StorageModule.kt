package com.gammagamma.kas.di.modules

import com.gammagamma.kas.domain.storage.IStorageProvider
import com.gammagamma.kas.storage.HawkStorageProvider
import org.koin.dsl.module

val StorageModule = module {
    
    single<IStorageProvider> { HawkStorageProvider() }
    
}
