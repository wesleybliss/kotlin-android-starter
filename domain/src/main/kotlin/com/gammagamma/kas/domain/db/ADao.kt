package com.gammagamma.kas.domain.db

import com.gammagamma.kas.sqldelight.Database
import com.squareup.sqldelight.Transacter
import kotlinx.coroutines.flow.Flow

abstract class ADao<T>(protected val db: Database) {
    
    protected abstract val queries: Transacter
    
    abstract suspend fun getCountOnce(): Long
    
    abstract suspend fun getCount(): Flow<Long>
    
    abstract suspend fun getAll(): Flow<List<T>?>
    abstract suspend fun getById(id: Long): T?
    
    abstract suspend fun insert(value: T)
    
    abstract suspend fun insert(values: List<T>)
    
}
