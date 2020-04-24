package com.gammagamma.kas.db.dao

import com.gammagamma.kas.domain.db.AAddressDao
import com.gammagamma.kas.sqldelight.Database
import com.gammagamma.kas.sqldelight.data.Address
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import kotlinx.coroutines.flow.Flow

class AddressDao(db: Database) : AAddressDao(db) {
    
    override val queries by lazy { db.addressQueries }
    
    override suspend fun getCountOnce(): Long =
        queries.selectCountAddress().executeAsOne()
    
    override suspend fun getCount(): Flow<Long> =
        queries.selectCountAddress().asFlow().mapToOne()
    
    override suspend fun getAll(): Flow<List<Address>?> =
        queries.selectAllAddress().asFlow().mapToList()
    
    override suspend fun getById(id: Long): Address? =
        queries.selectByIdAddress(id).asFlow().mapToOne() as Address?
    
    override suspend fun insert(value: Address) {
        queries.insertObjectAddress(value)
    }
    
}
