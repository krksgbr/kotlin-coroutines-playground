package src

import kotlinx.coroutines.experimental.*
import kotlin.coroutines.experimental.coroutineContext


fun main(args: Array<String>) = runBlocking {
    val job = Job()

    launch(coroutineContext, parent = job) {
        try {
            delay(1000L)
            println("child1: This should get printed because I was not cancelled.")
        } catch (e: CancellationException) {
            println("child1: This should not get printed because I was not cancelled.")
        }
    }

    launch(coroutineContext, parent = job) {
        try {
            delay(2000L)
            println("child2: This should not get printed because I was cancelled.")
        } finally {
            println("child2: This should get printed because I was cancelled.")
        }
    }

    delay(1500L)
    job.cancelAndJoin()
}