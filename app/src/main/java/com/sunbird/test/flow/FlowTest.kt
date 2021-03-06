package com.sunbird.test.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


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

//fun main() = runBlocking {
//    val channel = Channel<Int> { }
//    launch {
//        for (x in 1..5) channel.send(x * x)
//    }
//
//    repeat(5) {
//        println(channel.receive())
//    }
//
//    println("Done!")
//}


//fun simple(): Flow<Int> = flow { // 流构建器
//    for (i in 1..3) {
//        delay(100) // 假装我们在这里做了一些有用的事情
//        println("emitting $i")
//        emit(i) // 发送下一个值
//    }
//}.flowOn(Dispatchers.Default)

//suspend fun performRequest(request: Int): String {
//    delay(1000)
//    return "response $request"
//}
//
//fun numbers(): Flow<Int> = flow {
//    try {
//        for (i in 1..100) {
//            println("emit $i")
//            emit(i)
//        }
//    } catch (e: Exception) {
//        e.printStackTrace()
//    } finally {
//        println("Finally in numbers")
//    }
//}
//
//fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")
//
//fun simple(): Flow<Int> = flow {
//    for (i in 1..3) {
//        delay(100) // 假装我们异步等待了 100 毫秒
//        emit(i) // 发射下一个值
//    }
//}

//fun main() = runBlocking {
////    withTimeoutOrNull(250) {
////        simple().collect(::println)
////    }
////    println("done")
//
////    (1..3).asFlow().transform {
////        emit("Making request $it")
////        emit(performRequest(it))
////    }.collect(::println)
////    numbers().take(2).collect { println(it) }
//
////    simple().collect { value ->
////        log("Collected $value")
////    }
//
//    val time = measureTimeMillis {
//        simple().conflate().collect { value ->
//            delay(300)
//            println(value)
//        }
//    }
//    println("Collected in $time ms")
//}


fun makeFlow() = flow {
    println("sending first value")

    withContext(Dispatchers.IO) {
        kotlinx.coroutines.delay(1000)
    }

    emit(1)
    println("first value collected,sending another value")

    withContext(Dispatchers.IO) {
        kotlinx.coroutines.delay(1000)
    }

    emit(2)
    println("second value collected,sending another value")
    withContext(Dispatchers.IO) {
        kotlinx.coroutines.delay(1000)
    }
    emit(3)
    println("done")
}


fun main() = runBlocking {
//    makeFlow().collect { value ->
//        withContext(Dispatchers.IO) {
//            kotlinx.coroutines.delay(1000)
//        }
//        println("got $value")
//    }
//
//    println("flow is completed")

//    val repeatableFlow = makeFlow().take(2)
//    println("first collection")
//    repeatableFlow.collect()
//    println("collecting again")
//    repeatableFlow.collect()
//    println("second collection completed")


//    flowOf("1", "2", "3").onStart {
//        emit("onStart")
//    }.onCompletion {
//        emit("onCompletion")
//    }.onEach {
//        println("onEach:$it")
//    }.collect {
//        println("collect:$it")
//    }

    emptyFlow<String>().onEmpty {
        emit("onEmpty")
    }.onStart {
//        emit("onStart")
    }.onEach {
        println("onEach:$it")
    }.onCompletion {
//        emit("onCompletion")
    }.collect {
        println("collect:$it")
    }

}












