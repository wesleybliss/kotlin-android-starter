package com.gammagamma.kas.domain.db

interface IDao<T> {
    
    fun getAll(): List<T>
    
    fun getById(userId: Int): T
    
    fun insertAll(vararg users: T)
    
    fun delete(user: T)
    
}
