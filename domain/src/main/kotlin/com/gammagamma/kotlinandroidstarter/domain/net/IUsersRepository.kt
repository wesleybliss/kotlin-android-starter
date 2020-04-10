package com.gammagamma.kotlinandroidstarter.domain.net

import com.gammagamma.kotlinandroidstarter.domain.model.User

interface IUsersRepository : IRepository<User> {
    
    suspend fun fetchByEmail(email: String): User
    
}
