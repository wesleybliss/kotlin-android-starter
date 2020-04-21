package com.gammagamma.kas.network.repository

import com.gammagamma.kas.domain.net.Result
import com.gammagamma.kas.network.service.ApiService
import com.gammagamma.kas.domain.net.IUsersRepository
import com.gammagamma.kas.logging.plankE
import com.gammagamma.kas.network.mapper.UserResponseMapper
import com.gammagamma.kas.sqldelight.data.User

class UserRepository(private val apiService: ApiService) : IUsersRepository {
    
    // @todo DI
    private val mapper by lazy { UserResponseMapper() }
    
    override suspend fun fetchAll(): Result<List<User>> = try {
        Result.Success(mapper.map(apiService.getUsers()))
    } catch (e: Exception) {
        plankE(e)
        Result.Error(e)
    }
    
    override suspend fun fetchById(id: Int): Result<User> = try {
        Result.Success(mapper.map(apiService.getUserById(id)))
    } catch (e: Exception) {
        plankE(e)
        Result.Error(e)
    }
    
    override suspend fun fetchByEmail(email: String): User =
        mapper.map(apiService.getUserByEmail(email))
    
}
