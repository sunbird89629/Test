package com.sunbird.test.coroutine

import android.os.Build
import android.view.View
import kotlinx.coroutines.Job

/**
 * 作者：王豪
 * 日期：2021/6/1
 * 描述：<必填>
 */

fun Job.asAutoDisposable(view: View) = AutoDisposableJob(view, this)

class AutoDisposableJob(
    private val view: View,
    private val wrapped: Job
) : Job by wrapped, View.OnAttachStateChangeListener {

    private fun isViewAttached() = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT -> view.isAttachedToWindow
        else -> view.windowToken != null
    }

    init {
        if (isViewAttached()) {
            view.addOnAttachStateChangeListener(this)
        } else {
            cancel()
        }

        invokeOnCompletion {
            view.post {
                view.removeOnAttachStateChangeListener(this)
            }
        }
    }

    override fun onViewAttachedToWindow(v: View?) = Unit

    override fun onViewDetachedFromWindow(v: View?) {
        cancel()
        view.removeOnAttachStateChangeListener(this)
    }
}