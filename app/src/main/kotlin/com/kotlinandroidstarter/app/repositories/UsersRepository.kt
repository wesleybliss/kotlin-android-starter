package com.kotlinandroidstarter.app.repositories

import com.kotlinandroidstarter.app.api.ApiService
import com.kotlinandroidstarter.app.api.apiCall
import com.kotlinandroidstarter.app.models.User

class UsersRepository(private val apiService: ApiService) {
    
    suspend fun fetchUsers() = apiCall<List<User>> {
        request = apiService.getUsers("Test")
    }
    
    /*suspend fun fetchUsers() : LiveData<Result<MutableList<User>>> {
        val result = mutableLiveDataOf<Result<MutableList<User>>>(Result.Loading("Fetching users"))
        GlobalScope.launch(Dispatchers.Main) {
            result.postValue(
                try {
                    Result.Success(apiService.getUsers("Test").await().toMutableList())
                } catch (e: Exception) {
                    Result.Error(RuntimeException("Failed to fetch users"))
                }
            )
        }
        return result
    }*/
    
}
