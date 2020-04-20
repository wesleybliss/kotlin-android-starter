package com.gammagamma.kas.domain.db

import kotlinx.coroutines.flow.Flow

// @todo make ID have iface like CustomDatabaseID
interface IDao<ID, T> {
    
    suspend fun getCountOnce(): Long
    
    suspend fun getCount(): Flow<Long>
    
    suspend fun getAll(): Flow<List<T>?>
    suspend fun getById(id: ID): Flow<T?>
    
    suspend fun insert(value: T)
    
}
