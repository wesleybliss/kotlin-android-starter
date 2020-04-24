package com.gammagamma.kas.di.module

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gammagamma.kas.db.dao.AddressDao
import com.gammagamma.kas.db.dao.UserDao
import com.gammagamma.kas.domain.db.AAddressDao
import com.gammagamma.kas.domain.db.AUserDao
import com.gammagamma.kas.sqldelight.Database
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.dsl.module

val DatabaseModule = module {
    
    single<SqlDriver> { createAndroidSqliteDriver(get()) }
    single { createDatabase(get()) }
    
    single<AUserDao> { UserDao(get()) }
    single<AAddressDao> { AddressDao(get()) }
    
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
