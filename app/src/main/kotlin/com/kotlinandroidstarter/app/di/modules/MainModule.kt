package com.kotlinandroidstarter.app.di.modules

import com.gammagamma.kotlinandroidstarter.network.repository.IUsersRepository
import com.gammagamma.kotlinandroidstarter.network.repository.impl.UsersRepository
import com.kotlinandroidstarter.app.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val MainModule = module {
    
    viewModel { MainViewModel() }
    
    single<IUsersRepository> { UsersRepository(get()) }
    
    /*factory<AArticlesAdapter<*>> {
        (
            items: MutableList<IArticle>,
            onRowClick: (IArticle, Int) -> Unit
        ) -> ArticlesAdapter(items, onRowClick)
    }*/
    
}
