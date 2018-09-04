package src

import kotlin.coroutines.experimental.buildIterator


fun numbersFrom(start: Int) = buildIterator {
    var x = start
    while (true) {
        println("numberFrom yielding: $x")
        yield(x++)
    } // infinite stream of integers from start
}


fun filter(numbers: Iterator<Int>, prime: Int) = buildIterator {
    for (x in numbers) {
        if (x % prime != 0) {
            yield(x)
        }
    }
}

fun main(args: Array<String>) {
    var cur = numbersFrom(2)
    for (i in 1..10) {
        val prime = cur.next()
        cur = filter(cur, prime)
    }
}
