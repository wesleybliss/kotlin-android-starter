package com.kotlinandroidstarter.app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orhanobut.hawk.Hawk

class SharedViewModel : ViewModel() {
    
    companion object {
        const val KEY_TEST_VALUE = "key_test_value"
    }
    
    private val testValue: MutableLiveData<String> by lazy(mode = LazyThreadSafetyMode.NONE) {
        
        val data = MutableLiveData<String>()
        val value = Hawk.get<String>(KEY_TEST_VALUE)
        
        data.value = value ?: "Test Value Unknown"
        
        return@lazy data
        
    }
    
    fun setTestValue(value: String) {
        Hawk.put(KEY_TEST_VALUE, value)
        testValue.value = value
    }
    
    fun getTestValue(): LiveData<String> = testValue
    
}
