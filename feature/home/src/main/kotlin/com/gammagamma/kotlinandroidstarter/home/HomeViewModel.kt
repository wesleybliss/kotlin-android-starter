package com.gammagamma.kotlinandroidstarter.home

import androidx.lifecycle.viewModelScope
import com.gammagamma.kotlinandroidstarter.domain.model.IUser
import com.gammagamma.kotlinandroidstarter.domain.net.IUsersRepository
import com.gammagamma.kotlinandroidstarter.domain.net.Result
import com.gammagamma.kotlinandroidstarter.ui.StatefulBaseViewModel
import com.gammagamma.kotlinandroidstarter.ui.extensions.mutableLiveDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeViewModel : KoinComponent, /*AHomeViewModel,*/ StatefulBaseViewModel() {
    
    private val usersRepository: IUsersRepository by inject()
    
    val users = mutableLiveDataOf(listOf<IUser>()) { fetchUsers() }
    
    fun fetchUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            loading.postValue(true)
            val res = usersRepository.fetchAll()
            if (res is Result.Error) error.postValue(res.exception.message)
            else users.postValue((res as Result.Success<List<IUser>>).data)
            loading.postValue(false)
        }
    }
    
}
