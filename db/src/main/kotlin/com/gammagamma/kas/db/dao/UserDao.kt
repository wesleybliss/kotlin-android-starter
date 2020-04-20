package com.gammagamma.kas.db.dao

import com.gammagamma.kas.domain.db.AddressId
import com.gammagamma.kas.domain.db.IUserDao
import com.gammagamma.kas.domain.db.UserId
import com.gammagamma.kas.domain.model.Address
import com.gammagamma.kas.domain.model.User
import com.gammagamma.kas.sqldelight.Database
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import kotlinx.coroutines.flow.Flow

class UserDao(private val db: Database) : IUserDao {
    
    override suspend fun getCountOnce(): Long = db.userQueries
        .selectCount().executeAsOne()
    
    override suspend fun getCount(): Flow<Long> = db.userQueries
        .selectCount().asFlow().mapToOne()
    
    override suspend fun getAll(): Flow<List<User>?> = db.userQueries
        //.selectAll().asFlow().mapToList() as Flow<List<User>?>
        .selectAll(mapper = {
            userId: UserId,
            email: String,
            name: String?,
            _: AddressId?,
            phone: String?,
            addressId: AddressId?,
            street: String?,
            suite: String?,
            city: String?,
            zipcode: String? ->
            User(
                id = userId,
                email = email,
                name = name,
                address = Address(
                    addressId,
                    street,
                    suite,
                    city,
                    zipcode
                ),
                phone = phone
            )
        }).asFlow().mapToList()
    
    override suspend fun getById(id: UserId): Flow<User?> = db.userQueries
            .selectById(id, mapper = {
                userId: UserId,
                email: String,
                name: String?,
                _: AddressId?,
                phone: String?,
                addressId: AddressId?,
                street: String?,
                suite: String?,
                city: String?,
                zipcode: String? ->
                User(
                    id = userId,
                    email = email,
                    name = name,
                    address = Address(
                        addressId,
                        street,
                        suite,
                        city,
                        zipcode
                    ),
                    phone = phone
                )
            }).asFlow().mapToOne()
    
    override suspend fun insert(value: User) {
        db.userQueries.insert(
            value.id,
            value.email ?: "",
            value.name,
            value.address.id,
            value.phone
        )
    }
    
}
