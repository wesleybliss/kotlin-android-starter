package com.gammagamma.kas.di.module

import com.gammagamma.kas.db.dao.AddressDao
import com.gammagamma.kas.db.dao.UserDao
import com.gammagamma.kas.domain.db.IAddressDao
import com.gammagamma.kas.domain.db.IUserDao
import org.koin.dsl.module

val DatabaseModule = module { 
    
    single<IUserDao> { UserDao(get()) }
    single<IAddressDao> { AddressDao(get()) }
    
}
