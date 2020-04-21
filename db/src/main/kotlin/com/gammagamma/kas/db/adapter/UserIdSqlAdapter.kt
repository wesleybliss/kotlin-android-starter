package com.gammagamma.kas.db.adapter

import com.gammagamma.kas.sqldelight.data.Address
import com.squareup.sqldelight.ColumnAdapter

/*
import com.gammagamma.kas.domain.db.UserId
import com.squareup.sqldelight.ColumnAdapter

object UserIdSqlAdapter : ColumnAdapter<UserId, Long> {
    
    override fun decode(databaseValue: Long): UserId = UserId(databaseValue)
    
    override fun encode(value: UserId): Long = value.value
    
} 
*/

object UserIdSqlAdapter : ColumnAdapter<Address, Long> {
    
    override fun decode(databaseValue: Long): Address {
        TODO("Not yet implemented")
    }
    
    override fun encode(value: Address): Long {
        TODO("Not yet implemented")
    }
    
}
