package com.gammagamma.kotlinandroidstarter.storage

import android.content.Context
import com.gammagamma.kotlinandroidstarter.domain.storage.IStorageProvider
import com.orhanobut.hawk.Hawk
import com.orhanobut.hawk.HawkBuilder
import org.koin.core.KoinComponent
import org.koin.core.get

class HawkStorageProvider : KoinComponent, IStorageProvider {
    
    override fun init() = Hawk.init(get<Context>()).build()
    
    override fun <T> put(key: String?, value: T): Boolean = Hawk.put(key, value)
    
    override fun <T> get(key: String?): T? = Hawk.get(key)
    
    override fun <T> get(key: String?, defaultValue: T): T = Hawk.get(key, defaultValue)
    
    override fun count(): Long = Hawk.count()
    
    override fun deleteAll(): Boolean = Hawk.deleteAll()
    
    override fun delete(key: String?): Boolean = Hawk.delete(key)
    
    override fun contains(key: String?): Boolean = Hawk.contains(key)
    
    override fun isBuilt(): Boolean = Hawk.isBuilt()
    
    override fun destroy() = Hawk.destroy()
    
}

