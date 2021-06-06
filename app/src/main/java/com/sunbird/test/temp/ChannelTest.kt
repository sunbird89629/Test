package com.sunbird.test.temp

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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

fun main() = runBlocking {
    producer.join()
    consumer.join()
}