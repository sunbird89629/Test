package com.sunbird.test.recyclerview

import android.view.View
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.sqrt

/**
 * 作者：王豪
 * 日期：2021/11/15
 * 描述：<必填>
 */
fun RecyclerView.smoothScrollToPositionWithOffset(position: Int, offset: Int) {
    val linearSmoothScroller = object : LinearSmoothScroller(context) {
        /**
         * Called when the target position is laid out. This is the last callback SmoothScroller
         * will receive and it should update the provided [Action] to define the scroll
         * details towards the target view.
         *
         * @param targetView The view element which render the target position.
         * @param state      Transient state of RecyclerView
         * @param action     Action instance that you should update to define final scroll action
         * towards the targetView
         */
        override fun onTargetFound(targetView: View, state: RecyclerView.State, action: Action) {
            super.onTargetFound(targetView, state, action)
            val dx = calculateDxToMakeVisible(targetView, horizontalSnapPreference)
            val dy = calculateDyToMakeVisible(targetView, SNAP_TO_START)
            val distance = sqrt((dx * dx + dy * dy).toDouble()).toInt()
            val time = calculateTimeForDeceleration(distance)
            if (time > 0) {
                action.update(-dy, -dy - offset, time, mDecelerateInterpolator)
            }
        }
    }.apply {
        targetPosition = position
    }

    layoutManager?.startSmoothScroll(linearSmoothScroller)
}