package src

import kotlinx.coroutines.experimental.*


fun main(args: Array<String>) = runBlocking<Unit> {
    val jobs = arrayListOf<Job>()
    jobs += launch(Unconfined) {
        // not confined -- will work with main thread
        println("      'Unconfined': I'm working in thread ${Thread.currentThread().name}")
    }
    jobs += launch(kotlin.coroutines.experimental.coroutineContext) {
        // context of the parent, runBlocking coroutine
        println("'coroutineContext': I'm working in thread ${Thread.currentThread().name}")
    }
    jobs += launch(CommonPool) {
        // will get dispatched to ForkJoinPool.commonPool (or equivalent)
        println("      'CommonPool': I'm working in thread ${Thread.currentThread().name}")
    }

    jobs += launch(IO) {
        println("      'IO': I'm working in thread ${Thread.currentThread().name}")
    }
    jobs += launch(newSingleThreadContext("MyOwnThread")) {
        // will get its own new thread
        println("          'newSTC': I'm working in thread ${Thread.currentThread().name}")
    }
    jobs.forEach { it.join() }


    // launch {...} = launch(DefaultDispatcher) = launch(CommonPool) {}
}