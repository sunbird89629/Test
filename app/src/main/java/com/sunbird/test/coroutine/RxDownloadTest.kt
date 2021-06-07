package com.sunbird.test.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.newCoroutineContext
import java.io.File
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * 作者：王豪
 * 日期：2021/5/31
 * 描述：<必填>
 */


sealed class DownloadStatus {
    object None : DownloadStatus()
    class Progress(val value: Int) : DownloadStatus()
    class Error(val throwable: Throwable) : DownloadStatus()
    class Done(val file: File) : DownloadStatus()
}


//fun download(url: String, fileName: String): Flowable<DownloadStatus> {
//    return Flowable.create<DownloadStatus>({
//        val request = Request.Builder().url(url).get().build()
//        val okHttpClient = OkHttpClient.Builder().build()
//        val response = okHttpClient.newCall(request).execute()
//        if(response.isSuccessful){
//        }
//    })
//}


//public fun CoroutineScope.launch(
//    context: CoroutineContext = EmptyCoroutineContext,
//    start: CoroutineStart = CoroutineStart.DEFAULT,
//    block: suspend CoroutineScope.() -> Unit
//): Job {
//    val newContext = newCoroutineContext(context)
//    val coroutine = if (start.isLazy) {
//
//    }
//}