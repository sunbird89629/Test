package com.sunbird.test.viewmodel

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sunbird.test.R
import org.xutils.common.util.LogUtil

class TimerActivity : AppCompatActivity() {

    val timerViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        ).get(TimerWithLiveDataViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        initComponent()

        LogUtil.d("called.....s")

    }

    private fun initComponent() {
        val tvTime = findViewById<TextView>(R.id.tv_time)
        timerViewModel.currentSecond.observe(this) {
            tvTime.text = "Time:${it}"
        }

        timerViewModel.startTimer()

        findViewById<Button>(R.id.btn_reset).setOnClickListener {
            timerViewModel.currentSecond.value = 0
        }

        val listener = MyLocationListener(this) { latitude, longitude ->
            LogUtil.i("latitude:${latitude},longitude:${longitude}")
        }

        lifecycle.addObserver(listener)
    }
}