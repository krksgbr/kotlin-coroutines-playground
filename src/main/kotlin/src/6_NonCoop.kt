package src

import kotlinx.coroutines.experimental.cancelAndJoin
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking


// Coroutine cancellation is cooperative.
// A coroutine code has to cooperate to be cancellable.
// All the suspending functions in kotlinx.coroutines are cancellable.
// They check for cancellation of coroutine and throw CancellationException when cancelled.
// However, if a coroutine is working in a computation and does not check for cancellation, then it cannot be cancelled, like the following example shows:
fun main(args: Array<String>) = runBlocking<Unit> {
    val startTime = System.currentTimeMillis()
    val job = launch {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) {
            // computation loop, just wastes CPU
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("I'm sleeping ${i++} ...")
                nextPrintTime += 1000L
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}