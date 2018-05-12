package com.kotlinandroidstarter.app.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kotlinandroidstarter.app.models.Post
import com.kotlinandroidstarter.app.repos.ApiRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsViewModel @Inject constructor(private val repository: ApiRepo) : ViewModel() {
    
    val error: MutableLiveData<Throwable> by lazy { 
        MutableLiveData<Throwable>()
    }
    
    val posts: MutableLiveData<List<Post>> by lazy {
        // Initial fetch
        fetchPosts()
        // Default value
        MutableLiveData<List<Post>>()
    }
    
    fun fetchPosts() {
        repository.fetchPosts().subscribe(
            { posts.value = it },
            { error.value = it }
        )
    }
    
}
