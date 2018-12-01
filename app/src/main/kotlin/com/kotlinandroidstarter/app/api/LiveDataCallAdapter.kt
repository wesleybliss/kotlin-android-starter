package com.kotlinandroidstarter.app.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A Retrofit adapter that converts the Call into a LiveData of ApiResponse.
 * @param <R>
</R> */
class LiveDataCallAdapter<R>(private val responseType: Type)
    : CallAdapter<R, LiveData<ApiResponse<R>>> {
    
    override fun responseType() = responseType
    
    override fun adapt(call: Call<R>) : LiveData<ApiResponse<R>> =
        object : LiveData<ApiResponse<R>>() {
            
            private var started = AtomicBoolean(false)
            
            override fun onActive() {
                
                super.onActive()
                
                if (started.compareAndSet(false, true))
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(ApiResponse.create(response))
                        }

                        override fun onFailure(call: Call<R>, throwable: Throwable) {
                            postValue(ApiResponse.create(throwable))
                        }
                    })
                
            }
        }
    
}

/**
 * A Retrofit adapter that converts the Call into a MutableLiveData of ApiResponse.
 * @param <R>
</R> */
class MutableLiveDataCallAdapter<R>(private val responseType: Type)
    : CallAdapter<R, MutableLiveData<ApiResponse<R>>> {
    
    override fun responseType() = responseType
    
    override fun adapt(call: Call<R>) : MutableLiveData<ApiResponse<R>> =
        object : MutableLiveData<ApiResponse<R>>() {
            
            private var started = AtomicBoolean(false)
            
            override fun onActive() {
                
                super.onActive()
                
                if (started.compareAndSet(false, true))
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(ApiResponse.create(response))
                        }
                        
                        override fun onFailure(call: Call<R>, throwable: Throwable) {
                            postValue(ApiResponse.create(throwable))
                        }
                    })
                
            }
        }
    
}
