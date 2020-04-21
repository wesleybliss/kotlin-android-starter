package com.gammagamma.kas.domain.net

import com.gammagamma.kas.sqldelight.data.User

interface IUsersRepository : IRepository<User> {
    
    suspend fun fetchByEmail(email: String): User
    
}
