package com.sunbird.cryptobot.util

import android.util.Log
import com.sunbird.test.BuildConfig

/**
 * 作者：王豪
 * 日期：2021/6/15
 * 描述：One Key log utils
 */
object OKLog {
     val TAG = OKLog::class.java.simpleName
    fun i(message: String) {
        val tag = generateTag()
        Log.i(tag, message)
    }

   inline fun d (msg:()->String){
        if(BuildConfig.DEBUG){

            val tag = generateTag()
            Log.d(tag, msg())
        }
    }

    fun d(message: String) {
        val tag = generateTag()
        Log.d(tag, message)
    }

     inline fun  generateTag(): String {
        val caller = Throwable().stackTrace[0]
        val simpleClassName = caller.className.takeLastWhile { it != '.' }
        return "$TAG->${simpleClassName}.${caller.methodName}(L:${caller.lineNumber})"
    }
}
