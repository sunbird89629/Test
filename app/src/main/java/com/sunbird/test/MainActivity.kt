package com.sunbird.test

import android.content.Intent
import android.os.*
import android.util.SparseArray
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sunbird.cryptobot.util.OKLog
import com.sunbird.test.viewmodel.TimerActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.xutils.common.util.LogUtil
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {
    private val handler: Handler = object : Handler(Looper.myLooper()!!) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
        }
    }

    private val mSharedFlow: SharedFlow<String> = MutableStateFlow("test1")

//        MainActivityHandler(requireNotNull(Looper.myLooper()))

    val myHandlerThread: HandlerThread by lazy {
        val thread = HandlerThread("test")
        thread.start()
        thread
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            mSharedFlow.collect {
                OKLog.i("it:${it}")


            }
        }
        OKLog.d {
            "66666666"
        }
//        lifecycleScope.launch {
//            is(isActive){
//
//            }
//        }

        Looper.getMainLooper().setMessageLogging {
            OKLog.i("====> $it")
        }

        handler.post {

        }
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

        testSparseArray()
    }

    fun testHandlerThread() {
        Handler(myHandlerThread.looper).post {
            OKLog.i(Thread.currentThread().name)
            OKLog.i("called......")
        }
    }

    private fun testSparseArray() {
        val array = SparseArray<String>()
        for (i in 0..100) {
            array.put(i, "value for $i")
        }
        println("array.size()=${array.size()}")
    }
}

class MainActivityHandler(looper: Looper) : Handler(looper) {
    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
    }
}
