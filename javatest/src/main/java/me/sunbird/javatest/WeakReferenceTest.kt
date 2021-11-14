package me.sunbird.javatest

import java.lang.ref.WeakReference

/**
 * 作者：王豪
 * 日期：2021/11/12
 * 描述：<必填>
 */


class MyObject {
    val byteArray = ByteArray(1024)
}

fun main() {
    val myWeekReferences = HashMap<String, WeakReference<MyObject>>()
    myWeekReferences.put("key1", WeakReference(MyObject()))
    myWeekReferences.put("key2", WeakReference(MyObject()))
    myWeekReferences.put("key3", WeakReference(MyObject()))
    System.gc()
//    Thread.sleep(1000)
//    System.gc()
//    Thread.sleep(1000)
    myWeekReferences.forEach { (t, u) ->
        println(u.get())
    }
}