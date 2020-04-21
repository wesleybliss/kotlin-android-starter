package com.gammagamma.kas.di.module

import androidx.room.Room
import com.gammagamma.kas.db.AppDatabase
import org.koin.dsl.module

val DatabaseModule = module { 
    
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            AppDatabase.databaseName!!
        ).build()
    }
    
    single { (db: AppDatabase) -> db.userDao() }
    single { (db: AppDatabase) -> db.addressDao() }
    
}
