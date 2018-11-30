package com.kotlinandroidstarter.app.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import com.kotlinandroidstarter.app.models.User
import kotlinx.coroutines.Deferred
import retrofit2.Response


interface ApiService {
    
    @GET("/users")
    fun getUsers(
        @Header("Foo") headerFoo: String
    ) : Deferred<Response<List<User>>>
    
}
