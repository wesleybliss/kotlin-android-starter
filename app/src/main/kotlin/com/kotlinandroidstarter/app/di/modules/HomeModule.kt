package com.kotlinandroidstarter.app.di.modules

import com.kotlinandroidstarter.app.features.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val HomeModule = module {
    
    viewModel { HomeViewModel(/*get()*/) }
    
}
