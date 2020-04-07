package com.gammagamma.kotlinandroidstarter.net.repository.impl

import com.gammagamma.domain.model.IUser
import com.gammagamma.domain.net.Result
import com.gammagamma.kotlinandroidstarter.net.models.User
import com.gammagamma.kotlinandroidstarter.net.service.ApiService
import com.gammagamma.kotlinandroidstarter.net.repository.IUsersRepository
import com.gammagamma.logging.plankE

class UsersRepository(private val apiService: ApiService) : IUsersRepository {
    
    override suspend fun fetchAll(): Result<List<IUser>> = try {
        Result.Success(apiService.getUsers())
    } catch (e: Exception) {
        plankE(e)
        Result.Error(e)
    }
    
    override suspend fun fetchById(id: Int): Result<IUser> = try {
        Result.Success(apiService.getUserById(id))
    } catch (e: Exception) {
        plankE(e)
        Result.Error(e)
    }
    
    override suspend fun fetchByEmail(email: String): IUser =
        apiService.getUserByEmail(email)
    
}
