package src

import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.selects.*
import java.util.*

fun asyncString(time: Int) = async {
    delay(time.toLong())
    "Waited for $time ms"
}

fun asyncStringsList(): List<Deferred<String>> {
    val random = Random(3)
    return List(12) { asyncString(random.nextInt(1000)) }
}

fun main(args: Array<String>) = runBlocking<Unit> {
    val list = asyncStringsList()
    val result = select<String> {
        list.withIndex().forEach { (index, deferred) ->
            deferred.onAwait { answer ->
                "Deferred $index produced answer '$answer'"
            }
        }
    }
    println(result)
    val countActive = list.count { it.isActive }
    println("$countActive coroutines are still active")
}

