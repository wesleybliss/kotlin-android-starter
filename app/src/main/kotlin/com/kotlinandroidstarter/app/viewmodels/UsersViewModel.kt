package com.kotlinandroidstarter.app.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.kotlinandroidstarter.app.models.User
import com.kotlinandroidstarter.app.repos.ApiRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersViewModel @Inject constructor(val repository: ApiRepo) : ViewModel() {

    val error = MutableLiveData<Throwable>()
    val users = MutableLiveData<List<User>>()
    
    /*val users: MutableLiveData<List<User>> by lazy {
        // Initial fetch
        fetchUsers()
        // Default value
        MutableLiveData<List<User>>()
    }*/
    
    fun fetchUsers() {
        repository.fetchUsers().subscribe(
            { users.value = it },
            { error.value = it }
        )
    }
    
}
