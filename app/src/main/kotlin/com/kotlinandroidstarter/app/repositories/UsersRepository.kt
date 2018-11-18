package com.kotlinandroidstarter.app.repositories

import com.kotlinandroidstarter.app.api.ApiService
import com.kotlinandroidstarter.app.api.Result
import com.kotlinandroidstarter.app.api.safeApiCall
import com.kotlinandroidstarter.app.models.User
import kotlinx.coroutines.Deferred

class UsersRepository(private val apiService: ApiService) {

    suspend fun fetchUsers() : Deferred<Result<List<User>>> =
        safeApiCall(
            { Result.Success(apiService.getUsers("Test").await()) },
            "Failed to fetch users"
        )
    
}
