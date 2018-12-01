package com.kotlinandroidstarter.app.di

import com.kotlinandroidstarter.app.api.AppExecutors
import com.kotlinandroidstarter.app.repositories.UsersRepository
import com.kotlinandroidstarter.app.viewmodels.SharedViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object AppModule {
    
    val module = module {}
    
}

object ViewModule {
    
    val module = module {
        
        viewModel { SharedViewModel(get()) }
        
        single { AppExecutors() }
        
    }
    
}

object RepositoryModule {
    
    val module = module {
        
        single { UsersRepository(get(), get()) }
        
    }
    
}
