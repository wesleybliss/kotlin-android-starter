package com.gammagamma.kas.domain.net

import com.gammagamma.kas.sqldelight.data.User

interface IUserRepository : IRepository<User> {
    
    suspend fun fetchByEmail(email: String): User
    
}
