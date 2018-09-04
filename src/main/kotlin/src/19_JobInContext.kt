package src

import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.runBlocking
import kotlin.coroutines.experimental.coroutineContext


fun main(args: Array<String>) = runBlocking<Unit> {
    println("My job is ${coroutineContext[Job]}")
    println("Job: $Job")
    println(isActive == coroutineContext[Job]?.isActive)
}

