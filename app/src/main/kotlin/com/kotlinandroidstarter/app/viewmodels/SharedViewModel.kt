package com.kotlinandroidstarter.app.viewmodels

import androidx.lifecycle.LiveData
import com.kotlinandroidstarter.app.repositories.UsersRepository
import com.kotlinandroidstarter.app.utils.Storage
import com.kotlinandroidstarter.extensions.mutableLiveDataOf

class SharedViewModel(private val usersRepository: UsersRepository) : BaseViewModel() {
    
    companion object {
        const val KEY_TEST_VALUE = "key_test_value"
    }
    
    val users = usersRepository.fetchUsers()
    
    private val testValue = mutableLiveDataOf(
        Storage.testValue, "Test Value Unknown")
    
    fun setTestValue(value: String) {
        Storage.testValue = value
        testValue.value = value
    }
    
    fun getTestValue(): LiveData<String> = testValue
    
    fun fetchUsers() {
        // Reassign the value, causing a get, which will
        // read from the local DB or fetch from the API
        // (I think...
        users.value?.let { users.value = it }
    }
    
}
