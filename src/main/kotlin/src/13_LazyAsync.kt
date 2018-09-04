package src

import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import kotlin.system.measureTimeMillis


fun main(args: Array<String>) = runBlocking<Unit> {
    val time = measureTimeMillis {
        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
        // some computation
        one.start() // start the first one
        two.start() // start the second one
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}


// Note, that if we have called await in println and omitted start
// on individual coroutines, then we would have got the sequential
// behaviour as await starts the coroutine execution and waits
// for the execution to finish,  which is not the intended use-case
// for laziness. The use-case for async(start = CoroutineStart.LAZY)
// is a replacement for the standard lazy function in cases when
// computation of the value involves suspending functions.

