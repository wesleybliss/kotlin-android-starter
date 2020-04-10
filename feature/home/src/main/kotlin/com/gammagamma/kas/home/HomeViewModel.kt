package com.gammagamma.kas.home

import com.gammagamma.kas.domain.model.User
import com.gammagamma.kas.domain.net.IUsersRepository
import com.gammagamma.kas.ui.StatefulBaseViewModel
import com.gammagamma.kas.ui.extensions.mutableLiveDataOf
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeViewModel : KoinComponent, StatefulBaseViewModel() {
    
    private val usersRepository: IUsersRepository by inject()
    
    val users = mutableLiveDataOf(listOf<User>()) { fetchUsers() }
    
    fun fetchUsers() {
        networkRequest(usersRepository::fetchAll, { users.postValue(it) }, 5)
    }
    
}
