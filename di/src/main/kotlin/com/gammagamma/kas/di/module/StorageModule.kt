package com.gammagamma.kas.di.module

import androidx.room.Database
import com.gammagamma.kas.domain.storage.IStorageProvider
import com.gammagamma.kas.storage.HawkStorageProvider
import com.squareup.sqldelight.db.SqlDriver
import org.koin.dsl.module

val StorageModule = module {
    
    single<IStorageProvider> { HawkStorageProvider() }
    
    
    
}
