package com.sunbird.test.viewmodel

import android.app.Activity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import org.xutils.common.util.LogUtil

/**
 * 作者：王豪
 * 日期：2021/5/27
 * 描述：<必填>
 */
class MyLocationListener(
    val context: Activity,
    val onLocationChangeListener: (latitude: Double, longitude: Double) -> Unit
) : LifecycleObserver {
    init {
        initLocationManager()
    }

    private fun initLocationManager() {
        LogUtil.i("init location manager")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun startGetLocation() {
        LogUtil.i("start get location")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun stopGetLocation() {
        LogUtil.i("stop get location")
    }
}