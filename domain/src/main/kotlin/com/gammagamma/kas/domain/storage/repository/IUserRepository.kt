package com.gammagamma.kas.domain.storage.repository

import com.gammagamma.kas.domain.model.User

interface IUserRepository {
    
    fun getAll(): List<User>
    
}
