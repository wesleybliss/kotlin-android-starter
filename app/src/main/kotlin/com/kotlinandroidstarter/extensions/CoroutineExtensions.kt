package com.kotlinandroidstarter.extensions

import kotlinx.coroutines.*
import java.util.concurrent.Executors

/**
 * Hello, and welcome to The Future™!
 *
 * These helper extensions simplify the syntax of making
 * network calls via Kotlin's native coroutines.
 */

/*
val executorService = Executors.newFixedThreadPool(100)!!
val networkDispatcher = executorService.asCoroutineDispatcher()

suspend fun <T> async(block: suspend CoroutineScope.() -> T) : Deferred<T> =
    async { block() }

suspend fun <T> asyncAwait(block: suspend CoroutineScope.() -> T) : T =
    async(block).await()

fun launch(block: suspend CoroutineScope.() -> Unit) : Job {
    */
/*val job: Job = launch(UI) { block() }
    asyncJobs.add(job)
    job.invokeOnCompletion { asyncJobs.remove(job) }*//*

    return GlobalScope.launch(Dispatchers.Main) { block() }
}

suspend inline fun <T> Unit.awesome(crossinline block: Unit.() -> T) : T =
    asyncAwait { block() }
*/
