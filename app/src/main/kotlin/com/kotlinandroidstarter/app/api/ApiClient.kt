package com.kotlinandroidstarter.app.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.kotlinandroidstarter.app.App
import com.kotlinandroidstarter.app.models.User
import timber.log.Timber

object ApiClient {
    
    private val TAG: String = ApiClient::class.java.simpleName
    
    fun fetchUsers(error: (reason: String) -> Unit, done: (users: List<User>) -> Unit) {
        
        App.api.getUsers("Test").enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>?, response: Response<List<User>>?) {
                if (response == null || !response.isSuccessful) {
                    Timber.w("onResponse unsuccessful result")
                    done(listOf())
                }
                else {
                    done(response.body()!!)
                }
            }
            override fun onFailure(call: Call<List<User>>?, t: Throwable?) {
                error(t?.message ?: "Unknown reason")
            }
        })
        
    }
    
}
