package src

import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking


fun produceInts() = produce<Int> {
    var x = 1 // start from 1
    while (true) {
        send(x++) // produce next
        delay(250) // wait 0.1s
    }
}


fun launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
    for (msg in channel) {
        println("Processor #$id received $msg")
    }
}


fun main(args: Array<String>) = runBlocking<Unit> {
    val producer = produceInts()
    repeat(5) { launchProcessor(it, producer) }
    delay(5000)
    producer.cancel() // cancel producer coroutine and thus kill them all
}


