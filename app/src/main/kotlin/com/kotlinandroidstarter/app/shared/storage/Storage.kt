package com.kotlinandroidstarter.app.shared.storage

import com.orhanobut.hawk.Hawk

object Storage {
    
    fun <T> put(key: String?, value: T): Boolean = Hawk.put(key, value)
    fun <T> get(key: String?) : T? = Hawk.get(key)
    fun <T> get(key: String?, defaultValue: T?) : T? = Hawk.get(key, defaultValue)
    
    object Keys {
        
        const val foo = "foo"
        
    }
    
    val foo: Boolean
        get() = Hawk.get(Keys.foo, false)
    
}
