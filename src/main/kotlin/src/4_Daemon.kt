package src

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking


fun main(args: Array<String>) = runBlocking<Unit> {
    val j = launch {
        repeat(1000) { i ->
            println("I'm sleeping $i...")
            delay(500L)
        }
    }
    j.join()
    // delay(1300L)
}