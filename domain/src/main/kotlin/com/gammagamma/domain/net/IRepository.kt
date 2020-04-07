package com.gammagamma.domain.net

interface IRepository<T> {
    
    suspend fun fetchAll(): List<T>
    
    suspend fun fetchById(id: Int): T
    
}
