package com.sunbird.test.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * 作者：王豪
 * 日期：2021/6/5
 * 描述：<必填>
 */
//fun main() {
//    runBlocking {
//        val scope0 = this
//        //scope0是顶级携程
//        scope0.launch {
//            val scope1 = this
//            //scope1从scope0继承其上下文。它用自己的作业替换job字段，该作业是scope0中该作业的子级。
//            //它保留了Dispatcher字段，因此启动的携程使用runBlocking创建的程序调度
//            scope1.launch {
//                val scope2 = this
//                //scope2集成自scope1
//            }
//        }
//    }
//}

fun main() {
    runBlocking(Dispatchers.Main) {
        withContext(Dispatchers.IO) {}
        withContext(Dispatchers.Main) {}
        withContext(Dispatchers.IO) {}
        withContext(Dispatchers.Default) {}
    }
}