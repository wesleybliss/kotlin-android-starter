package com.gammagamma.kas.domain.net

import com.gammagamma.kas.domain.model.User

interface IUsersRepository : IRepository<User> {
    
    suspend fun fetchByEmail(email: String): User
    
}
