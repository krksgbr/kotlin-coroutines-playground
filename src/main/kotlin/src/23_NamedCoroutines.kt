package src

import kotlinx.coroutines.experimental.CoroutineName
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking


fun main(args: Array<String>) = runBlocking(CoroutineName("main")) {
    log("Started main coroutine")
    // run two background value computations
    val v1 = async(CoroutineName("my first coroutine ¯\\_(ツ)_/¯  ")) {
        delay(500)
        log("Computing v1")
        252
    }
    val v2 = async(CoroutineName("my second coroutine   (☞ﾟヮﾟ)☞   ")) {
        delay(1000)
        log("Computing v2")
        6
    }
    log("They say it's ${v1.await() / v2.await()}")
}
