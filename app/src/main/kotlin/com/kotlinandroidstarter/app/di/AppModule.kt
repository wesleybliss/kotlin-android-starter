package com.kotlinandroidstarter.app.di

import com.kotlinandroidstarter.app.viewmodels.SharedViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {}

val repositoryModule = module {
    
    //single { AuthRepository(get()) }
    //single { EventsRepository(get()) }
    //single { GuestsRepository(get()) }
    
    viewModel { SharedViewModel() }
    
}
