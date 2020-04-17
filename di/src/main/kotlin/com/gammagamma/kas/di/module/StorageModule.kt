package com.gammagamma.kas.di.module

import com.gammagamma.kas.Database
import com.gammagamma.kas.domain.storage.IStorageProvider
import com.gammagamma.kas.storage.HawkStorageProvider
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.dsl.module

val StorageModule = module {
    
    single<IStorageProvider> { HawkStorageProvider() }
    
    single<SqlDriver> { AndroidSqliteDriver(Database.Schema, get(), "kas.db") }
    single { Database(get()) }
    
}
