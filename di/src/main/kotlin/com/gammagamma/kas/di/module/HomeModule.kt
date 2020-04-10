package com.gammagamma.kas.di.module

import com.gammagamma.kas.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val HomeModule = module {
    
    viewModel { HomeViewModel() }
    
}
