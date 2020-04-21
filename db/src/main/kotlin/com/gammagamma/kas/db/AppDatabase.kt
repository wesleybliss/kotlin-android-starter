package com.gammagamma.kas.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gammagamma.kas.db.dao.AddressDao
import com.gammagamma.kas.db.dao.UserDao
import com.gammagamma.kas.db.model.Address
import com.gammagamma.kas.db.model.User

@Database(entities = [
    User::class,
    Address::class
], version = 1)
abstract class AppDatabase : RoomDatabase() {
    
    companion object {
        
        var databaseName: String? = null
            get() = if (field.isNullOrBlank())
                throw RuntimeException("Invalid database name") else field
        
    }
    
    abstract fun userDao(): UserDao
    abstract fun addressDao(): AddressDao
    
}
