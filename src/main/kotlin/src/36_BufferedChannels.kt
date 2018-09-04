package src

import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.channels.Channel
import kotlin.coroutines.experimental.coroutineContext


fun main(args: Array<String>) = runBlocking<Unit> {
    val channel = Channel<Int>(4) // create buffered channel
    val sender = launch(coroutineContext) {
        repeat(5) {
            // launch sender coroutine
            repeat(5) {
                println("Sending $it") // print before sending each element
                channel.send(it) // will suspend when buffer is full
                // don't receive anything... just wait....
                delay(250)
            }
            println("wait a sec...")
            delay(1000)
        }
    }

    delay(2000)
    launch(coroutineContext) {
        for (msg in channel) {
            println("receiving $msg")
        }
    }

    delay(10000)
    coroutineContext.cancelChildren() // cancel sender coroutine
}

