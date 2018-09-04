package src

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlin.coroutines.experimental.coroutineContext


fun main(args: Array<String>) = runBlocking<Unit> {

    val request = launch {
        // request completes when both its sub-jobs complete:
        // launch a coroutine to process some kind of incoming request
        // it spawns two other jobs, one with its separate context
        val job1 = launch {
            println("job1: I have my own context and execute independently!")
            delay(1000)
            println("job1: I am not affected by cancellation of the request")
        }
        // and the other inherits the parent context
        val job2 = launch(coroutineContext) {
            delay(100)
            println("job2: I am a child of the request coroutine")
            try {
                delay(1000)
                println("job2: I will not execute this line if my parent request is cancelled")
            } finally {
                println("job2: I've been cancelled.")
            }
        }
        job1.join()
        job2.join()
    }

    delay(500)
    request.cancel() // cancel processing of the request
    delay(1000) // delay a second to see what happens
    println("main: Who has survived request cancellation?")
}