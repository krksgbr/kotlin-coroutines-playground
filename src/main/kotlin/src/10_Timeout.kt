package src

import kotlinx.coroutines.experimental.*

//
//fun main(args: Array<String>) = runBlocking<Unit> {
//    try {
//        withTimeout(1300L) {
//            repeat(1000) { i ->
//                println("I'm sleeping $i ...")
//                delay(500L)
//            }
//        }
//    } catch (e: CancellationException) {
//        println("job got cancelled.")
//    }
//}


fun main(args: Array<String>) = runBlocking<Unit> {
    val result = withTimeoutOrNull(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i...")
            delay(500L)
        }
        "Result."
    }
    println("Result is $result.")
}