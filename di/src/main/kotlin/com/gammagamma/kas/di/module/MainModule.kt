package com.gammagamma.kas.di.module

import com.gammagamma.kas.domain.net.IUsersRepository
import com.gammagamma.kas.main.MainViewModel
import com.gammagamma.kas.network.repository.UsersRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val MainModule = module {
    
    viewModel { MainViewModel() }
    
    single<IUsersRepository> { UsersRepository(get()) }
    
}
