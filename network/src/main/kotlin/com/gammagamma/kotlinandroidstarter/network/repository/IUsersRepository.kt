package com.gammagamma.kotlinandroidstarter.network.repository

import com.gammagamma.domain.model.IUser
import com.gammagamma.domain.net.IRepository

interface IUsersRepository : IRepository<IUser> {
    
    suspend fun fetchByEmail(email: String): IUser
    
}
