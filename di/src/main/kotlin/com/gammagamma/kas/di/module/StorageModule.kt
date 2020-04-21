package com.gammagamma.kas.di.module

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gammagamma.kas.sqldelight.Database
import com.gammagamma.kas.sqldelight.data.Address
import com.gammagamma.kas.sqldelight.data.User
import com.gammagamma.kas.domain.storage.IStorageProvider
import com.gammagamma.kas.storage.HawkStorageProvider
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.dsl.module

val StorageModule = module {
    
    single<IStorageProvider> { HawkStorageProvider() }
    
    single<SqlDriver> { createAndroidSqliteDriver(get()) }
    single { createDatabase(get()) }
    
}

private fun createAndroidSqliteDriver(context: Context) = AndroidSqliteDriver(
    schema = Database.Schema,
    context = context,
    name = "kas.db",
    callback = object : AndroidSqliteDriver.Callback(Database.Schema) {
        // Enable foreign keys
        override fun onOpen(db: SupportSQLiteDatabase) {
            db.execSQL("PRAGMA foreign_keys=ON;")
        }
    }
)

private fun createDatabase(driver: SqlDriver) = Database(
    driver/*,
    Address.Adapter(AddressIdSqlAdapter),
    User.Adapter(UserIdSqlAdapter, AddressIdSqlAdapter)*/
)
