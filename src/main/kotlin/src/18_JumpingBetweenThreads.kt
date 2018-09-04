package src

import kotlinx.coroutines.experimental.newSingleThreadContext
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.withContext


fun main(args: Array<String>) {
    log("creating ctx1")
    newSingleThreadContext("Ctx1").use { ctx1 ->
        log("creating ctx2")
        newSingleThreadContext("Ctx2").use { ctx2 ->
            log("entering runBlocking")
            runBlocking(ctx1) {
                log("Started in ctx1")
                withContext(ctx2) {
                    log("Working in ctx2")
                }
                log("Back to ctx1")
            }
        }
    }
}