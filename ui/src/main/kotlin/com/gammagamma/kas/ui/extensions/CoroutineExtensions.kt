package com.gammagamma.kas.ui.extensions

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlin.coroutines.CoroutineContext

@InternalCoroutinesApi
suspend inline fun <T> Flow<T?>.collectNotNull(
    crossinline action: suspend (value: T) -> Unit
): Unit = collect(object : FlowCollector<T?> {
        override suspend fun emit(value: T?) {
            if (value != null) action(value)
        }
    })

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
