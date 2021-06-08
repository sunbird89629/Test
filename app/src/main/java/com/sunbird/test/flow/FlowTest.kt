package com.sunbird.test.flow

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * 作者：王豪
 * 日期：2021/6/8
 * 描述：<必填>
 */


//fun simple(): Sequence<Int> = sequence { // 序列构建器
//    for (i in 1..3) {
//        Thread.sleep(1000) // 假装我们正在计算
//        yield(i) // 产生下一个值
//    }
//}
//
//fun main() {
//    simple().forEach(::println)
//}


//fun simple(): Flow<Int>=flow{//流构建器
//    for (i in 1..3) {
//        delay(100)
//        emit(i)
//    }
//}


//fun simple(): Flow<Int> = flow {
//    for (i in 1..3) {
//        delay(100)
//        println("Emitting $i")
//        emit(i)
//    }
//}
//
//fun main() = runBlocking<Unit> {
//    withTimeoutOrNull(250) {
//        simple().collect(::println)
//    }
//}


//fun main() = runBlocking {
//    flow {
//        for (i in 1..5) {
//            delay(1000)
//            emit(i)
//        }
//    }.onCompletion { println("Done") }
//        .collect { println(it) }
//}


//fun main() = runBlocking {
//    flowOf(1, 2, 3, 4, 5).onEach {
//        println("onEach thread:${Thread.currentThread().name}")
//        delay(1000)
//    }.flowOn(Dispatchers.IO)
//        .collect {
//            println("collect thread:${Thread.currentThread().name}")
//            println(it)
//        }
//}

fun main() = runBlocking {
    val channel = Channel<Int> { }
    launch {
        for (x in 1..5) channel.send(x * x)
    }

    repeat(5) {
        println(channel.receive())
    }

    println("Done!")
}