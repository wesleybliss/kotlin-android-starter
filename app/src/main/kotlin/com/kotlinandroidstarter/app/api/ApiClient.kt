package com.kotlinandroidstarter.app.api

import com.kotlinandroidstarter.app.models.User

object ApiClient {
    
    private val TAG: String = ApiClient::class.java.simpleName
    
    fun fetchUsers(error: (reason: String) -> Unit, done: (users: List<User>) -> Unit) {
        
        /*App.api.getUsers("Test").enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>?, response: Response<List<User>>?) {
                if (response == null || !response.isSuccessful) {
                    Log.w(TAG, "onResponse unsuccessful result")
                    done(listOf<User>())
                }
                else {
                    done(response.body()!!)
                }
            }
            override fun onFailure(call: Call<List<User>>?, t: Throwable?) {
                error(t?.message ?: "Unknown reason")
            }
        })*/
        
    }
    
}