package com.gammagamma.kas.home

import androidx.lifecycle.viewModelScope
import com.gammagamma.kas.domain.db.IUserDao
import com.gammagamma.kas.domain.net.IUserRepository
import com.gammagamma.kas.sqldelight.data.User
import com.gammagamma.kas.ui.StatefulBaseViewModel
import com.gammagamma.kas.ui.extensions.collectNotNull
import com.gammagamma.kas.ui.extensions.liveDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

@InternalCoroutinesApi
class HomeViewModel : KoinComponent, StatefulBaseViewModel() {
    
    private val userDao: IUserDao by inject()
    private val userRepository: IUserRepository by inject()
    
    val users = liveDataOf(listOf<User>()) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.getAll().collectNotNull { postValue(it) }
        }
        fetchUsers()
    }
    
    /*
    // This could also be written like the following, but since
    // we don't need/want to manually change users, it makes more
    // sense to be LiveData
    //
    val users = mutableLiveDataOf(listOf<User>(), ::loadUsers)
    private fun loadUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.getAll().collectNotNull {
                users.postValue(it)
            }
        }
        fetchUsers()
    }
    */
    
    fun fetchUsers() {
        networkRequest(userRepository::fetchAll, 5)
    }
    
}
