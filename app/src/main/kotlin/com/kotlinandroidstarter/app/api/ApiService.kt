package com.kotlinandroidstarter.app.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.http.GET
import retrofit2.http.Header
import com.kotlinandroidstarter.app.models.User

interface ApiService {
    
    @GET("/users")
    fun getUsers(
        @Header("Foo") headerFoo: String
    ) : MutableLiveData<ApiResponse<List<User>>>
    
}
