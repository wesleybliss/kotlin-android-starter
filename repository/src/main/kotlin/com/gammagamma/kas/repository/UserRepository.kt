package com.gammagamma.kas.repository

import androidx.room.Database
import com.gammagamma.kas.Database
import com.gammagamma.kas.domain.model.User

class UserRepository(private val db: Database) {
    
    fun getAll(): List<User> = db.userQueries.selectAll().executeAsList() as List<User>
    
}
