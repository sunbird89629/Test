package com.sunbird.test.velocitytracker

import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


/**
 * 作者：王豪
 * 日期：2021/5/17
 * 描述：<必填>
 */
class VelocityTrackerTest : AppCompatActivity() {

    private lateinit var mInfo: TextView

    private val mVelocityTracker: VelocityTracker by lazy {
        VelocityTracker.obtain()
    }

    private val mMaxVelocity: Float by lazy {
        ViewConfiguration.get(this).scaledMaximumFlingVelocity.toFloat()
    }

    private var mPointerId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mInfo = TextView(this).apply {
            setLines(4)
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setTextColor(Color.BLACK)
        }
        setContentView(mInfo)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return false
        mVelocityTracker.addMovement(event)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mPointerId = event.getPointerId(0)
            }
            MotionEvent.ACTION_MOVE -> {
                mVelocityTracker.computeCurrentVelocity(1000, mMaxVelocity)
                val velocityX = mVelocityTracker.getXVelocity(mPointerId)
                val velocityY = mVelocityTracker.getYVelocity(mPointerId)
                recordInfo(velocityX, velocityY)
            }
            MotionEvent.ACTION_UP -> {
                releaseVelocityTracker()
            }
            MotionEvent.ACTION_CANCEL -> {
                releaseVelocityTracker()
            }
        }
        return super.onTouchEvent(event)
    }

    private fun releaseVelocityTracker() {
        mVelocityTracker.clear()
    }

    private fun recordInfo(velocityX: Float, velocityY: Float) {
        val info = "velocityX=${velocityX}...velocityY=${velocityY}"
        mInfo.text = info
    }
}