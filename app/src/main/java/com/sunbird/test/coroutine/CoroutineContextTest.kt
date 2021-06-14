package com.sunbird.test.temp

import kotlinx.coroutines.*

/**
 * 作者：王豪
 * 日期：2021/6/6
 * 描述：<必填>
 */

//fun main() {
//    var context = Job() + Dispatchers.IO + CoroutineName("aa")
//    println("$context, ${context[CoroutineName]}")
//}

//fun main() {
//    runBlocking {
//        var string = "4321"
////        async {
////
////        }
//        val job = launch {
//            delay(3000)
//            string = "1234"
//        }
//        println(string)
//        job.join()
//        println(string)
//    }
//}

fun main() {
//    GlobalScope.launch {
//        var string = "4321"
//        val job = launch {
//            delay(3000)
//            string = "1234"
//        }
//        println(string)
//        job.join()
//        println(string)
//    }
//    Thread.sleep(4000)


    runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (isActive) {
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job:I`m sleeping ${i++}...")
                    nextPrintTime += 500L
                }
            }
        }

        delay(1000L)
        job.cancelAndJoin()
    }
}
