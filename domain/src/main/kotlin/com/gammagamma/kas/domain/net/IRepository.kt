package com.gammagamma.kas.domain.net

interface IRepository<R : NetworkResponseObject, T> {
    
    suspend fun fetchAll(): Result<List<T>>
    
    suspend fun fetchById(id: Int): Result<T>
    
    suspend fun saveResponseData(data: R) : T
    
    suspend fun saveResponseData(data: List<R>) : List<T>
    
}
