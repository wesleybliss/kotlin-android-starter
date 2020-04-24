package com.gammagamma.kas.db.dao

import com.gammagamma.kas.domain.db.IUserDao
import com.gammagamma.kas.sqldelight.Database
import com.gammagamma.kas.sqldelight.data.User
import com.gammagamma.kas.sqldelight.data.UserWithAddress
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.transform

class UserDao(private val db: Database) : IUserDao {
    
    override suspend fun lastRowId(): Long = db.userQueries.userLastRowId().executeAsOne()
    
    override suspend fun getCountOnce(): Long = db.userQueries
        .selectCount().executeAsOne()
    
    override suspend fun getCount(): Flow<Long> = db.userQueries
        .selectCount().asFlow().mapToOne()
    
    override suspend fun getAll(): Flow<List<User>?> = db.userQueries
        /*.selectAll().asFlow().mapToList().map*/
        .usersOrderedById(mapper = {
            id,
            email,
            name,
            addressId,
            phone,
            addressId_,
            street,
            suite,
            city,
            zipcode ->  
            User.Impl(
                id,
                email,
                name,
                addressId,
                phone,
            )
        }).asFlow().mapToList()
        /*.selectAll(mapper = {
            id,
            email,
            name,
            address,
            phone,
            addressId,
            street,
            suite,
            city,
            zipcode ->
            User(
                id = id,
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
        }).asFlow().mapToList()*/
    
    override suspend fun getById(id: Long): Flow<User?> = db.userQueries
        .selectById(id).asFlow().mapToOne() as Flow<User?>
        /*.selectById(id, mapper = {
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
        }).asFlow().mapToOne()*/
    
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
