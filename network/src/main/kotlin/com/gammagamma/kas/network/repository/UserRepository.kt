package com.gammagamma.kas.network.repository

import com.gammagamma.kas.domain.model.User
import com.gammagamma.kas.domain.net.Result
import com.gammagamma.kas.network.service.ApiService
import com.gammagamma.kas.domain.net.IUsersRepository
import com.gammagamma.kas.logging.plankE

class UserRepository(private val apiService: ApiService) : IUsersRepository {
    
    override suspend fun fetchAll(): Result<List<User>> = try {
        Result.Success(apiService.getUsers())
    } catch (e: Exception) {
        plankE(e)
        Result.Error(e)
    }
    
    override suspend fun fetchById(id: Int): Result<User> = try {
        Result.Success(apiService.getUserById(id))
    } catch (e: Exception) {
        plankE(e)
        Result.Error(e)
    }
    
    override suspend fun fetchByEmail(email: String): User =
        apiService.getUserByEmail(email)
    
}
