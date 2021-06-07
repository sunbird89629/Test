package com.sunbird.test.temp

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

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

fun main() = runBlocking {
    producer.join()
    consumer.join()
}