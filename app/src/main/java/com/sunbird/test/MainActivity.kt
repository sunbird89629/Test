package com.sunbird.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.xutils.common.util.LogUtil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LogUtil.d("a test message")
    }
}