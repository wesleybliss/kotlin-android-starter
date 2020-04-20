package com.gammagamma.kas.db.dao

import com.gammagamma.kas.domain.db.AddressId
import com.gammagamma.kas.domain.db.IAddressDao
import com.gammagamma.kas.domain.model.Address
import com.gammagamma.kas.sqldelight.Database
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import kotlinx.coroutines.flow.Flow

// @todo I*dao should be abstract not iface, include db constructor??
class AddressDao(private val db: Database) : IAddressDao {
    
    // @todo this too?
    private val queries by lazy { db.addressQueries }
    
    override suspend fun getCountOnce(): Long = queries.selectCount().executeAsOne()
    
    override suspend fun getCount(): Flow<Long> = queries.selectCount().asFlow().mapToOne()
    
    override suspend fun getAll(): Flow<List<Address>?> = queries.selectAll(
        mapper = {
            id: AddressId,
            street: String,
            suite: String?,
            city: String?,
            zipCode: String? ->
            Address(
                id,
                street,
                suite,
                city,
                zipCode
            )
        }
    ).asFlow().mapToList()
    
    override suspend fun getById(id: AddressId): Flow<Address?> = queries
        .selectById(id, mapper = {
            id: AddressId,
            street: String,
            suite: String?,
            city: String?,
            zipcode: String? ->
            Address(
                id,
                street,
                suite,
                city,
                zipcode
            )
        })
        .asFlow().mapToOne()
    
    override suspend fun insert(value: Address) {
        queries.insert(
            value.id,
            value.street ?: "",
            value.suite,
            value.city,
            value.zipCode
        )
    }
}
