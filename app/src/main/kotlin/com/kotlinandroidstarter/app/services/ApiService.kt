package com.kotlinandroidstarter.app.services

import com.kotlinandroidstarter.app.models.Post
import com.kotlinandroidstarter.app.models.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    
    companion object {
        val BASE_URL = "https://jsonplaceholder.typicode.com"
    }
    
    @GET("/users")
    fun getUsers(
        @Header("Foo") headerFoo: String
    ) : Observable<List<User>>

    @GET("/posts")
    fun getPosts() : Observable<List<Post>>
    
}