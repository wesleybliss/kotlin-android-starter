package com.gammagamma.kas.db.dao

import com.gammagamma.kas.domain.db.AUserDao
import com.gammagamma.kas.sqldelight.Database
import com.gammagamma.kas.sqldelight.data.User
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import kotlinx.coroutines.flow.Flow

class UserDao(db: Database) : AUserDao(db) {
    
    override val queries by lazy { db.userQueries }
    
    override suspend fun getCountOnce(): Long = queries
        .selectCount().executeAsOne()
    
    override suspend fun getCount(): Flow<Long> = queries
        .selectCount().asFlow().mapToOne()
    
    override suspend fun getAll(): Flow<List<User>?> = queries
        .usersOrderedById(mapper = {
            id,
            email,
            name,
            addressId,
            phone,
            _,
            _,
            _,
            _,
            _ ->  
            User.Impl(
                id,
                email,
                name,
                addressId,
                phone,
            )
        }).asFlow().mapToList()
    
    override suspend fun getById(id: Long) = queries
        .selectById(id).asFlow().mapToOne() as User?
    
    override suspend fun insert(value: User) {
        queries.insertObject(value)
    }
    
    override suspend fun insert(values: List<User>) {
        queries.transaction { 
            values.forEach(queries::insertObject)
        }
    }
    
}
