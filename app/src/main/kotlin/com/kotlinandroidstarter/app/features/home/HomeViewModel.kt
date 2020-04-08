package com.kotlinandroidstarter.app.features.home

import androidx.lifecycle.viewModelScope
import com.gammagamma.domain.model.IUser
import com.gammagamma.domain.net.Result
import com.gammagamma.kotlinandroidstarter.network.repository.IUsersRepository
import com.kotlinandroidstarter.app.R
import com.kotlinandroidstarter.app.extensions.getString
import com.kotlinandroidstarter.app.extensions.mutableLiveDataOf
import com.kotlinandroidstarter.app.shared.ui.StatefulBaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeViewModel : KoinComponent, StatefulBaseViewModel() {
    
    private val usersRepository: IUsersRepository by inject()
    
    val title = getString(R.string.home_title)
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
