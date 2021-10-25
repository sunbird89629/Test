package com.sunbird.cryptobot.util

import android.util.Log

/**
 * 作者：王豪
 * 日期：2021/6/15
 * 描述：One Key log utils
 */
object OKLog {
    private val TAG = OKLog::class.java.simpleName
    fun i(message: String) {
        val tag = generateTag()
        Log.i(tag, message)
    }

    fun d(message: String) {
        val tag = generateTag()
        Log.d(tag, message)
    }

    private fun generateTag(): String {
        val caller = Throwable().stackTrace[2]
        val simpleClassName = caller.className.takeLastWhile { it != '.' }
        return "$TAG->${simpleClassName}.${caller.methodName}(L:${caller.lineNumber})"
    }
}
