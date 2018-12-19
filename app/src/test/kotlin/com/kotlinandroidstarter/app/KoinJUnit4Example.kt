package com.kotlinandroidstarter.app

import androidx.lifecycle.MutableLiveData
import com.kotlinandroidstarter.app.api.Resource
import com.kotlinandroidstarter.app.di.koinModules
import com.kotlinandroidstarter.app.models.User
import com.kotlinandroidstarter.app.repositories.UsersRepository
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.koin.test.declareMock

class KoinJUnit4Example : KoinTest {
    
    val usersRepository: UsersRepository by inject()
    
    @Before
    fun before() {
        startKoin(koinModules)
        declareMock<UsersRepository>()
    }
    
    @After
    fun after() {
        stopKoin()
    }
    
    @Test
    fun koinWithInjections() {
        
        val users = usersRepository.fetchUsers()
        
        assert(users is MutableLiveData<Resource<MutableList<User>>>)
        
        
    }
    
}
