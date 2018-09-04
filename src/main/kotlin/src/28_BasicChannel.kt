package src

import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>) = runBlocking<Unit> {
    val channel = Channel<Int>()
    launch {
        // this might be heavy CPU-consuming computation or async logic, we'll just send five squares
        for (x in 1..5) {
            delay(1000L)
            channel.send(x * x)
        }
    }
    // here we print five received integers:
    repeat(5) {
        println(channel.receive())
    }
    println("Done!")
}

