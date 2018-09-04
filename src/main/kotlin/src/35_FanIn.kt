package src

import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.SendChannel
import kotlin.coroutines.experimental.coroutineContext


suspend fun sendString(channel: SendChannel<String>, s: String, time: Long) {
    while (true) {
        delay(time)
        channel.send(s)
    }
}


fun main(args: Array<String>) = runBlocking<Unit> {
    val channel = Channel<String>()
    launch(coroutineContext) { sendString(channel, "foo", 250L) }
    launch(coroutineContext) { sendString(channel, "BAR!", 750L) }
    launch(coroutineContext) {
        for (msg in channel) {
            println(msg)
        }
    }
    delay(5000)
    coroutineContext.cancelChildren()
}