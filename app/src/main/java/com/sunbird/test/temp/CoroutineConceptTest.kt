package com.sunbird.test.temp

import kotlinx.coroutines.*

/**
 * 作者：王豪
 * 日期：2021/6/6
 * 描述：<必填>
 */
@ExperimentalStdlibApi
suspend fun test1_2() {
    coroutineScope {
        println("main context Dispatcher = ${this.coroutineContext[CoroutineDispatcher]}")
        println("main context Job = ${this.coroutineContext[Job]}")
        println("main context CoroutineName = ${this.coroutineContext[CoroutineName]}")
        println("main context CoroutineExceptionHandler = ${this.coroutineContext[CoroutineExceptionHandler]}")
        launch {
            println("main context Dispatcher = ${this.coroutineContext[CoroutineDispatcher]}")
            println("main context Job = ${this.coroutineContext[Job]}")
            println("main context CoroutineName = ${this.coroutineContext[CoroutineName]}")
            println("main context CoroutineExceptionHandler = ${this.coroutineContext[CoroutineExceptionHandler]}")
        }
    }
}

@ExperimentalStdlibApi
fun main(): Unit = runBlocking(CoroutineName("Main")) {
    test1_2()
    println("end")
}