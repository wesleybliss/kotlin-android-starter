package com.gammagamma.kas.di.module

import com.gammagamma.kas.domain.net.IUserRepository
import com.gammagamma.kas.network.mapper.AddressResponseMapper
import com.gammagamma.kas.network.mapper.UserResponseMapper
import com.gammagamma.kas.network.repository.UserRepository
import org.koin.dsl.module

val NetworkModule = module { 
    
    factory { UserResponseMapper() }
    factory { AddressResponseMapper() }
    
    single<IUserRepository> {
        UserRepository(
            apiService = get(),
            userMapper = get(),
            addressMapper = get(),
            userDao = get(),
            addressDao = get()
        )
    }
    
}
