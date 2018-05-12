package com.kotlinandroidstarter.app.repos

import android.util.Log
import com.kotlinandroidstarter.app.models.Post
import com.kotlinandroidstarter.app.models.User
import com.kotlinandroidstarter.app.services.ApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepo @Inject constructor(val apiService: ApiService) {
    
    fun fetchUsers() : Observable<List<User>> =
        apiService.getUsers("foo")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    
    fun fetchPosts() : Observable<List<Post>> =
        apiService.getPosts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

}
