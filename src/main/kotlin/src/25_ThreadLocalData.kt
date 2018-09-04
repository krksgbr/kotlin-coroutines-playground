package src


import kotlinx.coroutines.experimental.*

val threadLocal = ThreadLocal<String?>() // declare thread-local variable

fun main(args: Array<String>) = runBlocking<Unit> {
    threadLocal.set("main")
    println("Pre-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
    val job = launch(CommonPool + threadLocal.asContextElement(value = "launch"), start = CoroutineStart.UNDISPATCHED) {
        println("Launch start, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
        yield()
        println("After yield, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
    }
    job.join()
    println("Post-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
}

