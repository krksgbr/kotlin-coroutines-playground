package src

import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.channels.Channel
import kotlin.coroutines.experimental.coroutineContext


data class Ball(var hits: Int)

fun main(args: Array<String>) = runBlocking<Unit> {
    val table = Channel<Ball>() // a shared table
    launch(coroutineContext) { player("ping", table) }
    launch(newSingleThreadContext("PongThread")) { player("pong", table) }
    table.send(Ball(0)) // serve the ball
    delay(100)
    coroutineContext.cancelChildren() // game over, cancel them
}

suspend fun player(name: String, table: Channel<Ball>) {
    for (ball in table) { // receive the ball in a loop
        ball.hits++
        println("$name $ball | thread: ${Thread.currentThread().name}")
        // delay(300) // wait a bit
        table.send(ball) // send the ball back
    }
}