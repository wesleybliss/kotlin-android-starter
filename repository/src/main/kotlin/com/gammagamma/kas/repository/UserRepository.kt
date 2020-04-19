package com.gammagamma.kas.repository

import com.gammagamma.kas.sqldelight.Database
import com.gammagamma.kas.domain.db.UserId
import com.gammagamma.kas.domain.model.User

class UserRepository(private val db: Database) {
    
    fun getAll(): List<User> = db.userQueries.selectAll().executeAsList() as List<User>
    
    fun insert(value: User) {
        db.userQueries.insert(
            UserId(value.id),
            value.email,
            value.name,
            value.address_id,
            value.phone
        )
    }
    
}
