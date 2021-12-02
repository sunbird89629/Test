package com.sunbird.test

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.sunbird.cryptobot.util.OKLog
import com.sunbird.test.viewmodel.TimerActivity
import org.xutils.common.util.LogUtil
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    val myHandlerThread: HandlerThread by lazy {
        val thread = HandlerThread("test")
        thread.start()
        thread
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LogUtil.d("a test message")
        Timer().schedule(timerTask {
//            startActivity(Intent(this@MainActivity, MainActivity2::class.java))
//            startActivity(Intent(this@MainActivity, VelocityTrackerTest::class.java))
//            startActivity(Intent(this@MainActivity, NestedScrollTestActivity::class.java))
//            startActivity(Intent(this@MainActivity, LayoutManagerTestActivity::class.java))
//            startActivity(Intent(this@MainActivity, CoordinatorLayoutTest::class.java))
            startActivity(Intent(this@MainActivity, TimerActivity::class.java))
        }, 100)
        Looper.myQueue().addIdleHandler {
            OKLog.i("currentThread:${Thread.currentThread().name}")
            OKLog.i("method called......")
            true
        }
//        testHandlerThread()
    }

    fun testHandlerThread() {
        Handler(myHandlerThread.looper).post {
            OKLog.i(Thread.currentThread().name)
            OKLog.i("called......")
        }
    }
}
