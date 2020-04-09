package com.gammagamma.kotlinandroidstarter.home

import com.gammagamma.kotlinandroidstarter.domain.model.IUser
import com.gammagamma.kotlinandroidstarter.domain.net.IUsersRepository
import com.gammagamma.kotlinandroidstarter.ui.StatefulBaseViewModel
import com.gammagamma.kotlinandroidstarter.ui.extensions.mutableLiveDataOf
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeViewModel : KoinComponent, StatefulBaseViewModel() {
    
    private val usersRepository: IUsersRepository by inject()
    
    val users = mutableLiveDataOf(listOf<IUser>()) { fetchUsers() }
    
    fun fetchUsers() {
        networkRequest(usersRepository::fetchAll, { users.postValue(it) }, 5)
    }
    
}
