package src

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlin.coroutines.experimental.coroutineContext


fun main(args: Array<String>) = runBlocking<Unit> {
    // start a coroutine to process some kind of incoming request
    val request = launch(coroutineContext) {
        log("launched request")
        // use the context of `runBlocking`
        // spawns CPU-intensive child job in CommonPool !!!

        val job0 = launch(coroutineContext) {
            log("job0: Hi! I am a child of the request coroutine, with the same dispatcher.")
            try {
                delay(1000)
                log("job0: this should not get printed")
            } finally {
                log("job0: this will get printed when my parent is cancelled.")
            }

        }

        val job1 = launch(coroutineContext + CommonPool) {
            log("job: I am a child of the request coroutine, but with a different dispatcher")
            try {
                delay(1000)
                log("job1: this should not get printed")
            } finally {
                log("job1: this will get printed when my parent is cancelled.")
            }
        }

        job0.join()
        job1.join() // request completes when its sub-job completes
    }
    delay(500)
    request.cancel() // cancel processing of the request
    delay(1000) // delay a second to see what happens
    println("main: Who has survived request cancellation?")
}