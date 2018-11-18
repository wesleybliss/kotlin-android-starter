package com.kotlinandroidstarter.app.di

import com.kotlinandroidstarter.app.repositories.UsersRepository
import com.kotlinandroidstarter.app.viewmodels.SharedViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {}

val viewModule = module {
    
    viewModel { SharedViewModel(get()) }
    
}

val repositoryModule = module {
    
    single { UsersRepository(get()) }
    
}
