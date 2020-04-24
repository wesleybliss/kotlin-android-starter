package com.gammagamma.kas.di.module

import com.gammagamma.kas.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val MainModule = module {
    
    viewModel { MainViewModel() }
    
}
