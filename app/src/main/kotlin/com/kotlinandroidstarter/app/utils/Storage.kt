package com.kotlinandroidstarter.app.utils

import com.kotlinandroidstarter.app.viewmodels.SharedViewModel
import com.orhanobut.hawk.Hawk

object Storage {

    const val KEY_TEST_VALUE = "key_test_value"
    
    var testValue: String
        get() = Hawk.get<String>(SharedViewModel.KEY_TEST_VALUE) ?: ""
        set(value) { Hawk.put(KEY_TEST_VALUE, value) }
    
}
