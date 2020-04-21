package com.gammagamma.kas.db.dao

import androidx.room.*
import com.gammagamma.kas.db.entity.BaseEntity
import com.gammagamma.kas.logging.plank
import org.threeten.bp.OffsetDateTime

@Dao
abstract class BaseDao<T : BaseEntity> {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(obj: T) : Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    open suspend fun insert(vararg obj: T) : List<Long> =
        obj.map {
            insert(it.apply {
                createdAt = OffsetDateTime.now()
                modifiedAt = OffsetDateTime.now()
            })
        }
    
    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun updateRaw(obj: T)
    
    @Update(onConflict = OnConflictStrategy.REPLACE)
    open suspend fun update(obj: T) {
        
        updateRaw(obj.apply {
            modifiedAt = OffsetDateTime.now()
        })
        
    }
    
    @Delete
    abstract suspend fun delete(obj: T)
    
    @Transaction
    open suspend fun delete(vararg obj: T) {
        plank("@@@@ dao delete all: $obj")
        obj.forEach { delete(it) }
    }
    
}
