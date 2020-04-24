package com.gammagamma.kas.network.repository

import com.gammagamma.kas.domain.db.AAddressDao
import com.gammagamma.kas.domain.db.AUserDao
import com.gammagamma.kas.domain.net.Result
import com.gammagamma.kas.network.service.ApiService
import com.gammagamma.kas.domain.net.IUserRepository
import com.gammagamma.kas.domain.response.UserResponse
import com.gammagamma.kas.logging.plankE
import com.gammagamma.kas.network.mapper.AddressResponseMapper
import com.gammagamma.kas.network.mapper.UserResponseMapper
import com.gammagamma.kas.sqldelight.data.User
import org.koin.core.KoinComponent
import org.koin.core.inject

class UserRepository(
    private val apiService: ApiService,
    private val userMapper: UserResponseMapper,
    private val addressMapper: AddressResponseMapper,
    private val userDao: AUserDao,
    private val addressDao: AAddressDao
) : IUserRepository {
    
    override suspend fun fetchAll(): Result<List<User>> = try {
        
        val res = apiService.getUsers()
        val users = saveResponseData(res)
        
        Result.Success(users)
        
    } catch (e: Exception) {
        
        plankE(e)
        Result.Error(e)
        
    }
    
    override suspend fun fetchById(id: Int): Result<User> = try {
        
        val res = apiService.getUserById(id)
        val user = saveResponseData(res)
        
        Result.Success(user)
        
    } catch (e: Exception) {
        
        plankE(e)
        Result.Error(e)
        
    }
    
    override suspend fun fetchByEmail(email: String): User =
        userMapper.map(apiService.getUserByEmail(email))
    
    override suspend fun saveResponseData(data: UserResponse): User =
        saveResponseData(listOf(data)).first()
    
    override suspend fun saveResponseData(data: List<UserResponse>) : List<User> {
        
        val addresses = data.mapNotNull { it.address }
        val pendingUsers = userMapper.map(data)
        val pendingAddresses = addressMapper.map(addresses)
    
        addressDao.insert(pendingAddresses)
        userDao.insert(pendingUsers)
        
        return pendingUsers
        
    }
    
}
