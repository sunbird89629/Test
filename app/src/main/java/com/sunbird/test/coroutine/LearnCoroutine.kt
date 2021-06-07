package com.sunbird.test.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * 作者：王豪
 * 日期：2021/6/4
 * 描述：<必填>
 */


fun loadFromUrl(): kotlinx.coroutines.Job = GlobalScope.launch {
    val ioData = async {
        delay(1000)
        "mock io data"
    }

    val data = ioData.await()

    println(data)
}