package src

import kotlinx.coroutines.experimental.CoroutineExceptionHandler
import kotlinx.coroutines.experimental.JobCancellationException
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import java.io.IOException
import kotlin.coroutines.experimental.coroutineContext

fun main(args: Array<String>) = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught original $exception")
    }
    val job = launch(handler) {
        val inner = launch(coroutineContext) {
            launch(coroutineContext) {
                launch(coroutineContext) {
                    throw IOException()
                }
            }
        }
        try {
            inner.join()
        } catch (e: JobCancellationException) {
            println("Rethrowing JobCancellationException with original cause")
            throw e
        }
    }
    job.join()
}