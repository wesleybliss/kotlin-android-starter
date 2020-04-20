package com.gammagamma.kas.di.module

import com.gammagamma.kas.home.HomeViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@InternalCoroutinesApi
val HomeModule = module {
    
    viewModel { HomeViewModel() }
    
}
