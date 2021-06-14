package com.sunbird.test.temp

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * 作者：王豪
 * 日期：2021/6/6
 * 描述：<必填>
 */


//fun main() = runBlocking<Unit> {
//    println("start")
//    //例子开始
//    launch(Dispatchers.Unconfined) {
//        println("Unconfined      :I'm working in thread ${Thread.currentThread().name}")
//        delay(500)
//        println("Unconfined      :After delay int thread ${Thread.currentThread().name}")
//    }
//    launch {
//        println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
//        delay(1000)
//        println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
//    }
//    println("end")
//}

fun main() = runBlocking {
    val deffer = GlobalScope.async {
        val arg1 = suspendF1()
        var arg2 = suspendF2()
        println("suspend finish arg1:${arg1} arg2:${arg2} result:${arg1 + arg2}")
    }

    deffer.join()
}


suspend fun suspendF1(): Int {
    delay(1000)
    println("suspend fun1")
    return 2
}


suspend fun suspendF2(): Int {
    delay(1000)
    println("suspend fun2")
    return 4
}




