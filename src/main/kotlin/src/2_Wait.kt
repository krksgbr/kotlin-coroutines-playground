package src

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>) = runBlocking {
    //    val job = launch {
//        // launch new coroutine and keep a reference to its Job
//        delay(1000L)
//        println("World!")
//    }

    val job = launch {
        doWorld()
    }

    println("Hello,")
    job.join() // wait until child coroutine completes
    delay(1000L)
    println("Shit.")
}


suspend fun doWorld() {
    delay(1000L)
    println("World!")
}