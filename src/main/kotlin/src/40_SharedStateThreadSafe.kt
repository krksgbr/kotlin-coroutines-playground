package src

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.atomic.AtomicInteger

var atomicCounter = AtomicInteger()


fun main(args: Array<String>) = runBlocking<Unit> {
    massiveRun(CommonPool) {
        atomicCounter.incrementAndGet()
    }
    println("Counter =  ${atomicCounter.get()}")
}
