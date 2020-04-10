package com.gammagamma.kotlinandroidstarter.network.repository

import com.gammagamma.kotlinandroidstarter.domain.model.User
import com.gammagamma.kotlinandroidstarter.domain.net.Result
import com.gammagamma.kotlinandroidstarter.network.service.ApiService
import com.gammagamma.kotlinandroidstarter.domain.net.IUsersRepository
import com.gammagamma.logging.plankE

class UsersRepository(private val apiService: ApiService) : IUsersRepository {
    
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
