package com.gammagamma.kotlinandroidstarter.net.repository.impl

import com.gammagamma.domain.model.IUser
import com.gammagamma.kotlinandroidstarter.net.models.User
import com.gammagamma.kotlinandroidstarter.net.service.ApiService
import com.gammagamma.kotlinandroidstarter.net.repository.IUsersRepository

class UsersRepository(private val apiService: ApiService) : IUsersRepository {
    
    override suspend fun fetchAll(): List<IUser> =
        apiService.getUsers()
    
    override suspend fun fetchById(id: Int): IUser =
        apiService.getUserById(id)
    
    override suspend fun fetchByEmail(email: String): IUser =
        apiService.getUserByEmail(email)
    
}
