package com.gammagamma.kas.network.service

import com.gammagamma.kas.domain.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    
    @GET("/users")
    suspend fun getUsers(
        /*@Header("Foo") headerFoo: String*/
    ) : List<UserResponse>
    
    @GET("/users/:id")
    suspend fun getUserById(
        @Path("id") id: Int
    ) : UserResponse
    
    @GET("/users")
    suspend fun getUserByEmail(
        @Query("email") email: String
    ) : UserResponse
    
}
