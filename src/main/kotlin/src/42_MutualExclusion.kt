package src

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.sync.Mutex
import kotlinx.coroutines.experimental.sync.withLock


var mCounter = 0
var mutex = Mutex()


// The locking in this example is fine-grained, so it pays the price.
// However, it is a good choice for some situations where you absolutely must modify some shared state periodically, but there is no natural thread that this state is confined to.
fun main(args: Array<String>) = runBlocking<Unit> {
    massiveRun(CommonPool) {
        mutex.withLock {
            mCounter++
        }
    }
    println("Counter = $mCounter")
}

