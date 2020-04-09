package com.gammagamma.di.modules

import com.gammagamma.kotlinandroidstarter.domain.net.IUsersRepository
import com.gammagamma.kotlinandroidstarter.main.MainViewModel
import com.gammagamma.kotlinandroidstarter.network.repository.impl.UsersRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val MainModule = module {
    
    viewModel { MainViewModel() }
    
    single<IUsersRepository> { UsersRepository(get()) }
    
}
