package com.sunbird.test.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.concurrent.schedule

/**
 * 作者：王豪
 * 日期：2021/5/27
 * 描述：<必填>
 */
class TimerWithLiveDataViewModel : ViewModel() {

    private var timer: Timer? = null

    val currentSecond: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply {
            value = 1
        }
    }


    fun startTimer() {
        if (timer == null) {
            timer = Timer()
        }

        timer?.schedule(1000, 1000) {
            currentSecond.postValue(currentSecond.value!! + 1)
        }

    }
}