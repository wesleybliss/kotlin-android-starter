package com.kotlinandroidstarter.app.repos

import com.kotlinandroidstarter.app.models.User
import com.kotlinandroidstarter.app.services.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepo
@Inject constructor(private val apiService: ApiService) {
    
    fun fetchUsers(onSuccess: (List<User>) -> Unit, onError: (error: Throwable) -> Unit) =
        apiService.getUsers("foo")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(onSuccess, onError)
    
}