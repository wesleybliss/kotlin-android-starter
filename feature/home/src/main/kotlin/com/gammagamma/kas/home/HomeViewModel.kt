package com.gammagamma.kas.home

import androidx.lifecycle.viewModelScope
import com.gammagamma.kas.domain.db.IAddressDao
import com.gammagamma.kas.domain.db.IUserDao
import com.gammagamma.kas.domain.net.IUsersRepository
import com.gammagamma.kas.sqldelight.data.Address
import com.gammagamma.kas.sqldelight.data.User
import com.gammagamma.kas.ui.StatefulBaseViewModel
import com.gammagamma.kas.ui.extensions.collectNotNull
import com.gammagamma.kas.ui.extensions.mutableLiveDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import splitties.toast.toast

@InternalCoroutinesApi
class HomeViewModel : KoinComponent, StatefulBaseViewModel() {
    
    private val userDao: IUserDao by inject()
    private val addressDao: IAddressDao by inject()
    private val usersRepository: IUsersRepository by inject()
    
    //val users = mutableLiveDataOf(listOf<User>(), ::fetchUsers)
    val users = mutableLiveDataOf(listOf<User>(), ::loadUsers)
    
    fun loadUsers() {
        //storageRequest(userDao::getAll) { users.postValue(it) }
        viewModelScope.launch(Dispatchers.IO) {
            userDao.getAll().collectNotNull {
                users.postValue(it)
            }
        }
        fetchUsers()
        debugAddUser()
    }
    
    fun fetchUsers() {
        //networkRequest(usersRepository::fetchAll, { users.postValue(it) }, 5)
        networkRequest(usersRepository::fetchAll, { users ->
            viewModelScope.launch(Dispatchers.IO) {
                users?.forEach { userDao.insert(it) }
            }
        }, 5)
    }
    
    private fun debugAddUser() = viewModelScope.launch(Dispatchers.IO) {
        
        val userCount = userDao.getCountOnce()
        val addressCount = addressDao.getCountOnce()
        
        val newAddress = Address.Impl(
            addressCount + 1,
            "123 John St.",
            "Fl 4",
            "New York",
            "10012"
        )
        
        addressDao.insert(newAddress)
        
        delay(1000)
        
        val newUser = User.Impl(
            id = userCount + 1,
            email = "john@doe.com",
            name = "John Doe",
            addressId = newAddress.id,
            phone = ""
        )
            
        delay(3000)
        
        viewModelScope.launch(Dispatchers.Main) {
            toast("adding 1 user")
        }
        
        userDao.insert(newUser)
        
    }
    
}
