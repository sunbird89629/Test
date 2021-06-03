package com.sunbird.test.coroutine

import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * 作者：王豪
 * 日期：2021/5/29
 * 描述：<必填>
 */

//val continuation = suspend {
//    println("In Coroutine.")
//    5
//}.createCoroutine(object : Continuation<Int> {
//    /**
//     * Resumes the execution of the corresponding coroutine passing a successful or failed [result] as the
//     * return value of the last suspension point.
//     */
//    override fun resumeWith(result: Result<Int>) {
//        println("Coroutine End: ${result}")
//    }
//
//    /**
//     * The context of the coroutine that corresponds to this continuation.
//     */
//    override val context: CoroutineContext
//        get() = EmptyCoroutineContext
//
//})
//
//
//fun <R, T> launchCoroutine(receiver: R, block: suspend R.() -> T) {
//    block.startCoroutine(receiver, object : Continuation<T> {
//        /**
//         * The context of the coroutine that corresponds to this continuation.
//         */
//        override val context: CoroutineContext
//            get() = EmptyCoroutineContext
//
//        /**
//         * Resumes the execution of the corresponding coroutine passing a successful or failed [result] as the
//         * return value of the last suspension point.
//         */
//        override fun resumeWith(result: Result<T>) {
//            println("Coroutine End: $result")
//        }
//
//    })
//}

//@RestrictsSuspension
//class ProducerScope<T> {
//    suspend fun produce(value: T) {
//        println("the value is ${value}")
//    }
//}
//
//fun callLaunchCoroutine() {
//    launchCoroutine(ProducerScope<Int>()) {
//        println("In Coroutine.")
//        produce(1024)
//        delay(1000)
//        produce(2048)
//    }
//}

//suspend fun loadAndCombine(name1: String, name2: String): Image = coroutineScope {
//    val deffered1 = async { loadImage(name1) }
//    val deffered2 = async { loadImage(name2) }
//    combineImages(deffered1.await(), deffered2.await())
//}

//internal fun runSuspend(block: suspend () -> Unit) {
//    val run = RunSuspend()
//}


//fun main() {
////    continuation.resume(Unit)
//    println("start main")
//    println("before call coroutine")
////    callLaunchCoroutine()
//
//    runBlocking {
//        println("I am run blocking")
//    }
//
//    println("after call coroutine")
////    Thread.sleep(3000)
//    println("end main")
//}

//fun main() = runBlocking {
//    val job = GlobalScope.launch {
//        println("Throwing exception from launch")
//        throw IndexOutOfBoundsException()
//    }
//    job.join()
//    println("Joined failed job")
//    val deferred = GlobalScope.async {
//        println("Throwing exception from async")
//        throw ArithmeticException()
//    }
//
//    try {
//        deferred.await()
//        println("Unreached")
//    } catch (e: ArithmeticException) {
//        println("Caught ArithmeticException")
//    }
//}

//fun main() {
//    val job=async(CommonPool)
//}


//fun main() {
//    println("main start")
//    val job = GlobalScope.launch(Dispatchers.Main) {
//        println("start")
//        println(Thread.currentThread().name)
//        for (i in 1..100) {
//            println("current is $i")
//            delay(100)
//        }
//        println("end")
//    }
//    Thread.sleep(1000)
//    println("main end")
//}

//fun main() {
//    println("main start")
//    runBlocking(Dispatchers.Unconfined) {
//        println("blocking")
//        println("blocking method thread:${Thread.currentThread().name}")
//    }
//    println("main method thread:${Thread.currentThread().name}")
//    println("main end")
//}

//fun main() {
//    val job = GlobalScope.launch(Dispatchers.IO) {
//        val result = 1999 / 2
//        launch(Dispatchers.Main) {
//            println(result)
//        }
//    }
//    Thread.sleep(4000)
//}

suspend fun suspendFunc01(a: Int) {
    return
}

suspend fun suspendFunc02(s: String, b: String) = suspendCoroutine<String> { continuation ->
    thread {
        println("${s},${b}")
        Thread.sleep(1000)
        continuation.resumeWith(Result.success(b))
    }
}

//fun main() {
//    println("main method start")
//    println("coroutine start")
//    GlobalScope.launch {
//        suspend {
//            suspendFunc02("Hello", "Kotlin")
//            suspendFunc02("Hello", "Coroutine")
//        }.startCoroutine(object : Continuation<Int> {
//            /**
//             * The context of the coroutine that corresponds to this continuation.
//             */
//            override val context: CoroutineContext
//                get() = EmptyCoroutineContext
//
//            /**
//             * Resumes the execution of the corresponding coroutine passing a successful or failed [result] as the
//             * return value of the last suspension point.
//             */
//            override fun resumeWith(result: Result<Int>) {
//                println("result:${result.getOrNull()}")
//            }
//        })
//    }
//    println("coroutine end")
//    Thread.sleep(3000)
//    println("main method end")
//}


class LogInterceptor : ContinuationInterceptor {
    override val key = ContinuationInterceptor

    override fun <T> interceptContinuation(continuation: Continuation<T>) =
        LogContinuation(continuation)
}

class LogContinuation<T>(
    private val continuation: Continuation<T>
) : Continuation<T> by continuation {
    /**
     * Resumes the execution of the corresponding coroutine passing a successful or failed [result] as the
     * return value of the last suspension point.
     */
    override fun resumeWith(result: Result<T>) {
        println("before resumeWith: $result")
        continuation.resumeWith(result)
        println("after resumeWith.")
    }
}


//fun main() {
//    GlobalScope.launch {
//        suspend {
//            suspendFunc02("Hello", "Kotlin")
//            suspendFunc02("Hello", "Coroutine")
//        }.startCoroutine(object : Continuation<String> {
//            /**
//             * The context of the coroutine that corresponds to this continuation.
//             */
//            override val context = LogInterceptor()
//
//            /**
//             * Resumes the execution of the corresponding coroutine passing a successful or failed [result] as the
//             * return value of the last suspension point.
//             */
//            override fun resumeWith(result: Result<String>) {
//                println("result:${result.getOrNull()}")
//            }
//        })
//    }
//    Thread.sleep(4000)
//}


var continuation: Continuation<Int>? = null

suspend fun a(): Int {
    return b()
}

suspend fun b(): Int {
    while (true) {
        val i = suspendCoroutine<Int> { cont -> continuation = cont }
        if (i == 0) {
            return 0
        }
    }
}

fun main() {
    GlobalScope.launch {
        launch(Unconfined) {
            val a = a()
            println("Result is ${a}")
        }
        (10 downTo 0).forEach {
            continuation!!.resume(it)
        }
    }
    Thread.sleep(5000)
}


suspend fun delay(time: Long, unit: TimeUnit = TimeUnit.MILLISECONDS) {
    if (time <= 0) {
        return
    }
}




































