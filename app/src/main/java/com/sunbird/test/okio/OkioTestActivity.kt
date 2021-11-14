package com.sunbird.test.okio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sunbird.cryptobot.util.OKLog
import com.sunbird.test.R
import okio.buffer
import okio.source

class OkioTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okio_test)

        val inStream = resources.openRawResource(R.raw.test)
        val bufferedSource = inStream.source().buffer()
        bufferedSource.use {
            OKLog.i(it.readUtf8())
        }
    }
}