package com.sunbird.test.temp

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

/**
 * 作者：王豪
 * 日期：2021/6/6
 * 描述：<必填>
 */
val channel = Channel<Int>()

val producer = GlobalScope.launch {
    var i = 0
    while (true) {
        delay(1000)
        channel.send(i++)
    }
}

val consumer = GlobalScope.launch {
    while (true) {
        val element = channel.receive()
        println(element)
    }
}


suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // 假设我们在这里做了一些有用的事
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // 假设我们在这里也做了一些有用的事
    return 29
}


val time = runBlocking {
    val one = doSomethingUsefulOne()
    val two = doSomethingUsefulTwo()
}

//fun main() = runBlocking {
//    producer.join()
//    consumer.join()
//}

//fun main() = runBlocking {
//    val channel = Channel<Int>()
//    launch {
//        for (x in 1..5) {
//            delay(500)
//            channel.send(x * x)
//        }
//        channel.close()
//    }
//
//    for (y in channel) {
//        println(y)
//    }
//
//    println("Done!")
//}


//fun main() = runBlocking {
//    val squares = produceSquares()
//    squares.consumeEach { println(it) }
//    println("Done!")
//}

fun CoroutineScope.produceSquares(): ReceiveChannel<Int> = produce {
    for (x in 1..5) send(x * x)
}

fun CoroutineScope.produceNumbers() = produce<Int> {
    var x = 1
    while (true) send(x++)
}


fun CoroutineScope.square(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = produce {
    for (x in numbers) send(x * x)
}

fun main() = runBlocking {
    val numbers = produceNumbers()
    val squares = square(numbers)
    repeat(5) {
        println(squares.receive())
    }
    println("Done!")
    coroutineContext.cancelChildren()
}

