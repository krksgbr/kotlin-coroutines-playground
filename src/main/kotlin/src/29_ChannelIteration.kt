package src

import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>) = runBlocking {
    val channel = Channel<Int>()
    launch {
        for (x in 1..5) {
            delay(1000L)
            channel.send(x * x)
        }
        channel.close() // we're done sending
        // if commented out main will hang forever
        // the for loop will never break
    }
    // here we print received values using `for` loop (until the channel is closed)
    for (y in channel) println(y)
    println("Done!")
}