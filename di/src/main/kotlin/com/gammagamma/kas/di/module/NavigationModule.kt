package com.gammagamma.kas.di.module

import com.gammagamma.kas.di.Navigation
import com.kas.app.INavigation
import org.koin.dsl.module

val NavigationModule = module { 
    
    single<com.kas.app.INavigation> { Navigation }
    
}
