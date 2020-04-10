package com.gammagamma.kas.ui.extensions

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlin.coroutines.CoroutineContext

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
suspend fun <T> ReceiveChannel<T>.debounce(
    delayMillis: Long = 50,
    context: CoroutineContext = Dispatchers.Default
): ReceiveChannel<T> = withContext(context) {
    
    produce {
        
        var lastTimeout: Job? = null
        
        consumeEach {
            lastTimeout?.cancel()
            lastTimeout = launch {
                delay(delayMillis)
                send(it)
            }
        }
        
        lastTimeout?.join()
        
    }
    
}

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
suspend fun <T>ReceiveChannel<T>.throttle(
    delayMillis: Long = 200,
    context: CoroutineContext = Dispatchers.Default
): ReceiveChannel<T> = withContext(context){
    
    produce {
        
        var nextTime = 0L
        
        consumeEach {
            val curTime = System.currentTimeMillis()
            if (curTime >= nextTime) {
                nextTime = curTime + delayMillis
                send(it)
            }
        }
        
    }
    
}
