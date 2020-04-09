package com.gammagamma.kotlinandroidstarter.domain.net

import com.gammagamma.kotlinandroidstarter.domain.model.IUser

interface IUsersRepository : IRepository<IUser> {
    
    suspend fun fetchByEmail(email: String): IUser
    
}
