package com.gammagamma.kas.db.adapter

import com.gammagamma.kas.domain.db.UserId
import com.squareup.sqldelight.ColumnAdapter

object UserIdSqlAdapter : ColumnAdapter<UserId, Long> {
    
    override fun decode(databaseValue: Long): UserId = UserId(databaseValue)
    
    override fun encode(value: UserId): Long = value.value
    
} 
