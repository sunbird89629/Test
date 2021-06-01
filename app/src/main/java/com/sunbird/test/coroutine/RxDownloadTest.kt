package com.sunbird.test.coroutine

import io.reactivex.rxjava3.core.Flowable
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File

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