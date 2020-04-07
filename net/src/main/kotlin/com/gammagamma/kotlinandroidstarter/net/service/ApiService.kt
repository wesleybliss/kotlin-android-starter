package com.gammagamma.kotlinandroidstarter.net.service

import com.gammagamma.kotlinandroidstarter.net.models.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    
    @GET("/users")
    suspend fun getUsers(
        /*@Header("Foo") headerFoo: String*/
    ) : List<User>
    
    @GET("/users/:id")
    suspend fun getUserById(
        @Path("id") id: Int
    ) : User
    
    @GET("/users")
    suspend fun getUserByEmail(
        @Query("email") email: String
    ) : User
    
}
