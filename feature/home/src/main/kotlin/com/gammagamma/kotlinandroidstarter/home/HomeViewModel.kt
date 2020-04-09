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
        /*viewModelScope.launch(Dispatchers.IO) {
            loading.postValue(true)
            val res = usersRepository.fetchAll()
            if (res is Result.Error) error.postValue(filterError(res.exception).message)
            else users.postValue((res as Result.Success<List<IUser>>).data)
            loading.postValue(false)
        }*/
        /*networkRequest({ usersRepository.fetchAll() }) {
            users.postValue(it)
        }*/
        networkRequest(usersRepository::fetchAll, { users.postValue(it) }, 5)
    }
    
}
