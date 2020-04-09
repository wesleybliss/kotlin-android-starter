package com.gammagamma.di.modules

import com.gammagamma.kotlinandroidstarter.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val HomeModule = module {
    
    viewModel { HomeViewModel() }
    
}
