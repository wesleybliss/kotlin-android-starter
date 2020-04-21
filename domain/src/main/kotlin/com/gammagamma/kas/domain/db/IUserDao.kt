package com.gammagamma.kas.domain.db

import com.gammagamma.kas.domain.model.IUser

interface IUserDao /*: IDao<IUser>*/ {
    
    fun countRaw(): Int
    fun count(): LiveData<Int>
    fun getAllRaw(): List<User>
    fun getAll(): LiveData<MutableList<User>>
    fun getById(id: Int): User?
    fun updateTabRaw(obj: User)
    
}
