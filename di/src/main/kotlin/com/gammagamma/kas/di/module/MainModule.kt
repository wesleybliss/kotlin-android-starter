package com.gammagamma.kas.di.module

import com.gammagamma.kas.domain.net.IUserRepository
import com.gammagamma.kas.main.MainViewModel
import com.gammagamma.kas.network.repository.UserRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val MainModule = module {
    
    viewModel { MainViewModel() }
    
    single<IUserRepository> { UserRepository(get()) }
    
}
