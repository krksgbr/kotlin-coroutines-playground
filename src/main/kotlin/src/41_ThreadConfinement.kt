package src

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.newSingleThreadContext
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.withContext


val counterContext = newSingleThreadContext("CounterContext")
var confinedCounter = 0


// This code works very slowly, because it does fine-grained thread-confinement.
// Each individual increment switches from multi-threaded CommonPool context to the single-threaded context using withContext block.
//fun main(args: Array<String>) = runBlocking<Unit> {
//    massiveRun(CommonPool) {
//        // run each coroutine in CommonPool
//        withContext(counterContext) {
//            // but confine each increment to the single-threaded context
//            confinedCounter++
//        }
//    }
//    println("Counter = $confinedCounter")
//}


// In practice, thread confinement is performed in large chunks, e.g. big pieces of state-updating business logic are confined to the single thread.
// The following example does it like that, running each coroutine in the single-threaded context to start with.
fun main(args: Array<String>) = runBlocking<Unit> {
    massiveRun(counterContext) {
        confinedCounter++
    }
    println("Counter = $confinedCounter")
}



