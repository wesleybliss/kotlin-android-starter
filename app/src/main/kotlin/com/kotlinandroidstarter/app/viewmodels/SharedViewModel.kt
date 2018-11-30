package com.kotlinandroidstarter.app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlinandroidstarter.app.api.Result
import com.kotlinandroidstarter.app.models.User
import com.kotlinandroidstarter.app.repositories.UsersRepository
import com.kotlinandroidstarter.app.utils.Storage
import com.kotlinandroidstarter.extensions.mutableLiveDataOf
import kotlinx.coroutines.launch

class SharedViewModel(private val usersRepository: UsersRepository) : BaseViewModel() {
    
    companion object {
        const val KEY_TEST_VALUE = "key_test_value"
    }
    
    val users = mutableLiveDataOf(listOf<User>())
    
    private val testValue = mutableLiveDataOf(
        Storage.testValue, "Test Value Unknown")
    
    fun setTestValue(value: String) {
        Storage.testValue = value
        testValue.value = value
    }
    
    fun getTestValue(): LiveData<String> = testValue
    
    fun fetchUsers() {
        scope.launch {
            val req: MutableLiveData<Result<List<User>>> = usersRepository.fetchUsers().await()
            val res = req.value
            when (res) {
                is Result.Loading -> loading.value = true
                is Result.Success -> users.value = res.data
                is Result.Error -> error.value = res.exception.localizedMessage
            }
        }
    }
    
}
