package com.kotlinandroidstarter.app.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import com.kotlinandroidstarter.app.models.User


interface ApiService {
    
    @GET("/users")
    fun getUsers(
        @Header("Foo") headerFoo: String
    ) : Call<List<User>>
    
}