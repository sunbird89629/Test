package com.sunbird.test.viewmodel

import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.concurrent.schedule

/**
 * 作者：王豪
 * 日期：2021/5/26
 * 描述：<必填>
 */
class TimerViewModel : ViewModel() {
    var currentSecond: Int = 0
    private val timer: Timer by lazy {
        Timer()
    }

    var onTimeChangeListener: ((second: Int) -> Unit)? = null

    fun startTiming() {
        timer.schedule(1000, 1000) {
            currentSecond++
            onTimeChangeListener?.invoke(currentSecond)
        }
    }


    /**
     * 清理资源
     */
    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

}