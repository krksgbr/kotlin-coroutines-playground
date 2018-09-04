package src

import kotlinx.coroutines.experimental.channels.ticker
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.withTimeoutOrNull


fun main(args: Array<String>) = runBlocking<Unit> {
    val tickerChannel = ticker(delay = 100, initialDelay = 0) // create ticker channel
//    var nextElement = withTimeoutOrNull(1) { tickerChannel.receive() }
//    println("Initial element is available immediately: $nextElement") // initial delay hasn't passed yet
//
//    nextElement = withTimeoutOrNull(50) { tickerChannel.receive() } // all subsequent elements has 100ms delay
//    println("Next element is not ready in 50 ms: $nextElement")
//
//    nextElement = withTimeoutOrNull(60) { tickerChannel.receive() }
//    println("Next element is ready in 100 ms: $nextElement")
//
//    // Emulate large consumption delays
//    println("Consumer pauses for 150ms")
//    delay(150)
//    // Next element is available immediately
//    nextElement = withTimeoutOrNull(1) { tickerChannel.receive() }
//    println("Next element is available immediately after large consumer delay: $nextElement")
//    // Note that the pause between `receive` calls is taken into account and next element arrives faster
//    nextElement = withTimeoutOrNull(60) { tickerChannel.receive() }
//    println("Next element is ready in 50ms after consumer pause in 150ms: $nextElement")

    launch(kotlin.coroutines.experimental.coroutineContext) {
        repeat(10) { i ->
            val x = withTimeoutOrNull(50) { tickerChannel.receive() }
            println("$i: $x")
            if (i == 5) {
                delay(1000)
            }
        }
    }
}