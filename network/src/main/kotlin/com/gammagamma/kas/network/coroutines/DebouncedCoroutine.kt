package com.gammagamma.kas.network.coroutines

import kotlinx.coroutines.*

class ThrottledCoroutine(private val scope: CoroutineScope) {
    
    private var job: Job? = null
    
    fun debounce(delayMillis: Long, fn: suspend () -> Unit) : Job {
        
        if (job?.isCancelled != true) {
            job?.cancelChildren()
            job?.cancel()
        }
        
        // NOTE
        // The delay should be *inside* the body of the coroutine
        // Delaying in here will still somewhat work, but calls will get doubled up
        job = scope.launch {
            delay(delayMillis)
            fn()
        }
        
        return job!!
        
    }
    
}

fun CoroutineScope.throttle() =
    ThrottledCoroutine(this)
