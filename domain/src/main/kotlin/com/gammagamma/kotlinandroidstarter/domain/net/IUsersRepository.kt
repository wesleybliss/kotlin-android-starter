package com.gammagamma.kotlinandroidstarter.domain.net

import com.gammagamma.kotlinandroidstarter.domain.model.IUser
import com.gammagamma.kotlinandroidstarter.domain.net.IRepository

interface IUsersRepository : IRepository<IUser> {
    
    suspend fun fetchByEmail(email: String): IUser
    
}
