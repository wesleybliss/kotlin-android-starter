package com.gammagamma.kas.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gammagamma.kas.db.model.Address
import com.gammagamma.kas.db.model.User
import com.gammagamma.kas.domain.db.IDao

interface AddressDao : IDao<Address> {
    
    @Query("SELECT * FROM user")
    override fun getAll(): List<Address>
    
    @Query("SELECT * FROM user WHERE id = :userId")
    override fun getById(userId: Int): Address
    
    @Insert
    override fun insertAll(vararg users: Address)
    
    @Delete
    override fun delete(user: Address)
    
}
