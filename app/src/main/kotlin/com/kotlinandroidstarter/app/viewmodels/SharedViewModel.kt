package com.kotlinandroidstarter.app.viewmodels

import androidx.lifecycle.LiveData
import com.kotlinandroidstarter.app.api.Result
import com.kotlinandroidstarter.app.models.User
import com.kotlinandroidstarter.app.repositories.UsersRepository
import com.kotlinandroidstarter.app.utils.Storage
import com.kotlinandroidstarter.extensions.launch
import com.kotlinandroidstarter.extensions.mutableLiveDataOf

class SharedViewModel(private val usersRepository: UsersRepository) : BaseViewModel() {
    
    companion object {
        const val KEY_TEST_VALUE = "key_test_value"
    }
    
    val users = mutableLiveDataOf(mutableListOf<User>())
    
    private val testValue = mutableLiveDataOf(
        Storage.testValue, "Test Value Unknown")
    
    fun setTestValue(value: String) {
        Storage.testValue = value
        testValue.value = value
    }
    
    fun getTestValue(): LiveData<String> = testValue
    
    fun fetchUsers() {
        launch {
            val res = usersRepository.fetchUsers().await()
            when (res) {
                is Result.Success -> users.postValue(res.data.toMutableList())
                is Result.Error -> error.postValue(res.exception.localizedMessage)
            }
        }
    }
    
}
