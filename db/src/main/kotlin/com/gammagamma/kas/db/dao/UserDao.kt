package com.gammagamma.kas.db.dao

import com.gammagamma.kas.domain.db.IUserDao
import com.gammagamma.kas.sqldelight.Database
import com.gammagamma.kas.sqldelight.data.User
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import kotlinx.coroutines.flow.Flow

class UserDao(private val db: Database) : IUserDao {
    
    private val queries by lazy { db.userQueries }
    
    //override suspend fun lastRowId(): Long = db.userQueries.userLastRowId().executeAsOne()
    
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
        db.userQueries.insert(
            value.id,
            value.email,
            value.name,
            value.addressId,
            value.phone
        )
    }
    
}
