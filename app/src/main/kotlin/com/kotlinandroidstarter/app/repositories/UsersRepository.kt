package com.kotlinandroidstarter.app.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlinandroidstarter.app.api.*
import com.kotlinandroidstarter.app.models.User
import com.kotlinandroidstarter.extensions.liveDataOf
import com.vicpin.krealmextensions.queryAll
import com.vicpin.krealmextensions.saveAll
import timber.log.Timber

class UsersRepository(
    private val appExecutors: AppExecutors,
    private val apiService: ApiService) {
    
    fun fetchUsers() : MutableLiveData<Resource<MutableList<User>>> =
        object : NetworkBoundResource<MutableList<User>, List<User>>(appExecutors) {
            
            override fun saveCallResult(item: List<User>) {
                item.saveAll()
            }

            override fun shouldFetch(data: MutableList<User>?)
                : Boolean = data.isNullOrEmpty()
            
            override fun loadFromDb() : LiveData<MutableList<User>> =
                liveDataOf(User().queryAll().toMutableList())
            
            override fun createCall() = apiService.getUsers("Test")
            
        }.asMutableLiveData()

}
