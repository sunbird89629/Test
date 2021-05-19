package com.sunbird.test

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sunbird.test.layoutmanager.LayoutManagerTestActivity
import org.xutils.common.util.LogUtil
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LogUtil.d("a test message")
        Timer().schedule(timerTask {
//            startActivity(Intent(this@MainActivity, MainActivity2::class.java))
//            startActivity(Intent(this@MainActivity, VelocityTrackerTest::class.java))
//            startActivity(Intent(this@MainActivity, NestedScrollTestActivity::class.java))
            startActivity(Intent(this@MainActivity, LayoutManagerTestActivity::class.java))
        }, 100)
    }


}
