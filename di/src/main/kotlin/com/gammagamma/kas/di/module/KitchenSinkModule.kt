package com.gammagamma.kas.di.module

import com.gammagamma.kas.kitchensink.KitchenSinkViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val KitchenSinkModule = module { 
    
    viewModel { KitchenSinkViewModel() }
    
}
