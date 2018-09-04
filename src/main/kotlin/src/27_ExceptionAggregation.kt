package src

import kotlinx.coroutines.experimental.*
import java.io.IOException
import kotlin.coroutines.experimental.coroutineContext


fun main(args: Array<String>) = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception with suppressed ${exception.suppressed!!.contentToString()}")
    }
    val job = launch(handler + coroutineContext, parent = Job()) {
        launch(coroutineContext, start = CoroutineStart.ATOMIC) {
            try {
                delay(Long.MAX_VALUE)
            } finally {
                throw ArithmeticException()
            }
        }
        launch(coroutineContext) {
            throw IOException()
        }
        delay(Long.MAX_VALUE)
    }
    job.join()
}