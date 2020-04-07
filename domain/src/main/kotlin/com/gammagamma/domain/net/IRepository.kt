package com.gammagamma.domain.net

interface IRepository<T> {
    
    suspend fun fetchAll(): Result<List<T>>
    
    suspend fun fetchById(id: Int): Result<T>
    
}
