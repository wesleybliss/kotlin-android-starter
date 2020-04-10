package com.gammagamma.kas.storage

import com.gammagamma.kas.domain.storage.IStorageProvider
import org.koin.core.KoinComponent
import org.koin.core.inject

object Storage : KoinComponent {
    
    @Suppress("MemberVisibilityCanBePrivate")
    val provider: IStorageProvider by inject()
    
    fun <T> put(key: String?, value: T): Boolean = provider.put(key, value)
    fun <T> get(key: String?) : T? = provider.get(key)
    fun <T> get(key: String?, defaultValue: T?) : T? = provider.get(key, defaultValue)
    
    object Keys {
        
        //const val foo = "foo"
        
    }
    
    /*val foo: Boolean
        get() = provider.get(Keys.foo, false)*/
    
}
