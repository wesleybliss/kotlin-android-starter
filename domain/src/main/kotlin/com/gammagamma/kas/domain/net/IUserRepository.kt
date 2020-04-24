package com.gammagamma.kas.domain.net

import com.gammagamma.kas.domain.response.UserResponse
import com.gammagamma.kas.sqldelight.data.User

interface IUserRepository : IRepository<UserResponse, User> {
    
    suspend fun fetchByEmail(email: String): User
    
}
