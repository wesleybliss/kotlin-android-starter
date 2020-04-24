package com.gammagamma.kas.network.repository

import com.gammagamma.kas.domain.db.IAddressDao
import com.gammagamma.kas.domain.db.IUserDao
import com.gammagamma.kas.domain.net.Result
import com.gammagamma.kas.network.service.ApiService
import com.gammagamma.kas.domain.net.IUserRepository
import com.gammagamma.kas.logging.plankE
import com.gammagamma.kas.network.mapper.AddressResponseMapper
import com.gammagamma.kas.network.mapper.UserResponseMapper
import com.gammagamma.kas.sqldelight.data.User
import org.koin.core.KoinComponent
import org.koin.core.inject

class UserRepository(private val apiService: ApiService) : KoinComponent, IUserRepository {
    
    // @todo DI
    private val mapper by lazy { UserResponseMapper() }
    private val addressMapper by lazy { AddressResponseMapper() }
    
    private val userDao: IUserDao by inject()
    private val addressDao: IAddressDao by inject()
    
    override suspend fun fetchAll(): Result<List<User>> = try {
        
        val res = apiService.getUsers()
        
        val addresses = res.mapNotNull { it.address }
        addressMapper.map(addresses).forEach {
            //plank("Saving address $it")
            addressDao.insert(it)
        }
        
        val pendingUsers = mapper.map(res)
        pendingUsers.forEach { userDao.insert(it) }
        
        Result.Success(pendingUsers)
        
    } catch (e: Exception) {
        plankE(e)
        Result.Error(e)
    }
    
    override suspend fun fetchById(id: Int): Result<User> = try {
        Result.Success(mapper.map(apiService.getUserById(id)))
    } catch (e: Exception) {
        plankE(e)
        Result.Error(e)
    }
    
    override suspend fun fetchByEmail(email: String): User =
        mapper.map(apiService.getUserByEmail(email))
    
}
