package com.gammagamma.kotlinandroidstarter.net.repository

import com.gammagamma.domain.model.IUser
import com.gammagamma.kotlinandroidstarter.net.models.User
import com.gammagamma.domain.net.IRepository

interface IUsersRepository : IRepository<IUser> {
    
    suspend fun fetchByEmail(email: String): IUser
    
}
