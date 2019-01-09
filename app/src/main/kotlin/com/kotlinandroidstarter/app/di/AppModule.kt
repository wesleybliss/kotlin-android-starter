package com.kotlinandroidstarter.app.di

import android.content.Context
import android.os.SystemClock
import com.kotlinandroidstarter.app.api.AppExecutors
import com.kotlinandroidstarter.app.repositories.UsersRepository
import com.kotlinandroidstarter.app.viewmodels.FragBViewModel
import com.kotlinandroidstarter.app.viewmodels.SharedViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object AppModule {
    
    val module = module {
        
        factory { androidApplication() as Context }
        
    }
    
}

object ViewModule {
    
    val module = module {
        
        viewModel { SharedViewModel(get()) }
        viewModel { FragBViewModel() }
        
        single { AppExecutors() }
        
    }
    
}

object RepositoryModule {
    
    val module = module {
        
        single { UsersRepository(get(), get()) }
        
    }
    
}
