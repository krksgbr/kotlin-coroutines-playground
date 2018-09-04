package src

import kotlinx.coroutines.experimental.cancelAndJoin
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>) = runBlocking<Unit> {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(1000L)
            }
        } finally {
            println("I'm being cancelled.")
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    launch {
        repeat(3) { i ->
            println("main: will cancel in ${3 - i} second(s)")
            delay(1000L)
        }
    }.join()
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}

