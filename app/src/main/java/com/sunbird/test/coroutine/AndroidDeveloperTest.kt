package com.sunbird.test.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * 作者：王豪
 * 日期：2021/6/8
 * 描述：<必填>
 */
suspend fun fetchDocs() {
    val result = get()
    println(result)
}

suspend fun get(): String = withContext(Dispatchers.IO) {
    println("get method thread ${Thread.currentThread().name}")
    val httpClient = OkHttpClient.Builder().build()
    val request = Request.Builder().url("http://www.baidu.com").get().build()
    val call = httpClient.newCall(request)
    val response = call.execute()
    response.body?.string() ?: ""
}

fun main() = runBlocking {
//    println("main method thread ${Thread.currentThread().name}")
//    val result = get()
//    println("main method thread ${Thread.currentThread().name}")
//    println(result)
    println("result0 execute thread is ${Thread.currentThread().name}")

    val result1 = withContext(Dispatchers.IO) {
        println("result1 execute thread is ${Thread.currentThread().name}")
        "this is result from IO"
    }

    println("result1=${result1}")

    val result2 = withContext(Dispatchers.Unconfined) {
        println("result2 execute thread is ${Thread.currentThread().name}")
        "this is result from Unconfined"
    }

    println("result2=${result2}")

    val result3 = withContext(Dispatchers.Default) {
        println("result3 execute thread is ${Thread.currentThread().name}")
        "this is result from Default"
    }

    println("result3=${result3}")

}