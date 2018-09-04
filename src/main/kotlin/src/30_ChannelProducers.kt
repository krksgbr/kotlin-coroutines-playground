package src

import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.coroutines.experimental.channels.map
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking


fun produceSquares() = produce<Int> {
    for (x in 1..5) {
        delay(1000L)
        send(x * x)
    }
}

fun main(args: Array<String>) = runBlocking<Unit> {
    val squares = produceSquares()
    squares.map {
        it * 2
    }.consumeEach {
        println(it)
    }
    println("Done!")
}