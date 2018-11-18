package com.kotlinandroidstarter.app.api

import com.kotlinandroidstarter.app.App
import com.kotlinandroidstarter.app.models.User
import kotlinx.coroutines.Deferred

object ApiClient {
    
    suspend fun fetchUsers() : Deferred<Result<List<User>>> =
        safeApiCall(
            { Result.Success(App.apiService.getUsers("Test").await()) },
            "Failed to fetch users"
        )
    
}
