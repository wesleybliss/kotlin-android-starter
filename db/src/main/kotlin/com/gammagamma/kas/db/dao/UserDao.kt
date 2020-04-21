package com.gammagamma.kas.db.dao

import androidx.lifecycle.LiveData
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gammagamma.kas.db.model.User
import org.threeten.bp.OffsetDateTime

abstract class UserDao : BaseDao<User>() {
    
    @Query("SELECT COUNT(*) FROM user")
    abstract fun countRaw(): Int
    
    @Query("SELECT COUNT(*) FROM user")
    abstract fun count(): LiveData<Int>
    
    @Query("SELECT * FROM user ORDER BY modified_at desc")
    abstract fun getAllRaw(): List<User>
    
    @Query("SELECT * FROM user ORDER BY modified_at desc")
    abstract fun getAll(): LiveData<MutableList<User>>
    
    @Query("SELECT * FROM user WHERE id = :id LIMIT 1")
    abstract fun getById(id: Int): User?
    
    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun updateTabRaw(obj: User)
    
    /*@Update(onConflict = OnConflictStrategy.REPLACE)
    open suspend fun update(obj: User) {
        
        updateTabRaw(obj.apply {
            modifiedAt = OffsetDateTime.now()
        })
        
    }*/
    
}
