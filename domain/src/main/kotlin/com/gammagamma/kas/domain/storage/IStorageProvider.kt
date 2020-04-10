package com.gammagamma.kas.domain.storage

interface IStorageProvider {
    
    fun init()
    
    fun <T> put(key: String?, value: T): Boolean
    
    fun <T> get(key: String?): T?
    
    fun <T> get(key: String?, defaultValue: T): T
    
    fun count(): Long
    
    fun deleteAll(): Boolean
    
    fun delete(key: String?): Boolean
    
    fun contains(key: String?): Boolean
    
    fun isBuilt(): Boolean
    
    fun destroy()
    
}

