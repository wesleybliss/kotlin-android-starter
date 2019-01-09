package com.kotlinandroidstarter.app

import android.content.Context
import android.os.SystemClock
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kotlinandroidstarter.app.api.ApiService
import com.kotlinandroidstarter.app.api.AppExecutors
import com.kotlinandroidstarter.app.di.koinModules
import com.kotlinandroidstarter.app.repositories.UsersRepository
import io.realm.Realm
import io.realm.RealmConfiguration
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.koin.android.ext.koin.with
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.koin.test.declareMock
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

class KoinJUnit4Example : KoinTest {
    
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockContext: Context
    
    private val usersRepository: UsersRepository by inject()
    
    @Before
    fun before() {

        startKoin(koinModules) with (org.mockito.Mockito.mock(Context::class.java))
        
        //val app = App()
        Realm.init(mockContext)
        val testConfig = RealmConfiguration.Builder().inMemory().name("test-realm").build()
        val testRealm = Realm.getInstance(testConfig)
        Realm.setDefaultConfiguration(testConfig)
        
        declareMock< AppExecutors>()
        declareMock<ApiService>()
        //declareMock<UsersRepository>()
        /*usersRepository = get()
        things = usersRepository.fetchUsers()*/
    }
    
    @After
    fun after() {
        //stopKoin()
    }
    
    @Test
    fun koinWithInjections() {
        
        assert(usersRepository.fetchUsers() != null)
        
    }
    
}
