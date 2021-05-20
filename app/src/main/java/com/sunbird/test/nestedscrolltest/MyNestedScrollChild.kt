package com.sunbird.test.nestedscrolltest

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.NestedScrollingChild
import androidx.core.view.NestedScrollingChildHelper
import androidx.core.view.ViewCompat
import org.xutils.common.util.LogUtil
import kotlin.math.roundToInt

/**
 * 作者：王豪
 * 日期：2021/5/16
 * 描述：<必填>
 */
class MyNestedScrollChild : LinearLayoutCompat, NestedScrollingChild {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val mNestedScrollingChildHelper: NestedScrollingChildHelper by lazy {
        NestedScrollingChildHelper(this@MyNestedScrollChild).apply {
            isNestedScrollingEnabled = true
        }
    }
    private val offset = intArrayOf(0, 0)
    private val consumed = intArrayOf(0, 0)
    private var lastY = 0
    private var showHeight = 0

    private val mVelocityTracker: VelocityTracker by lazy {
        VelocityTracker.obtain()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        //第一次测量，因为布局文件中高度是wrap_content,因此测量模式为atmost,即高度不超过父控件的剩余空间
        showHeight = measuredHeight

        //第二次测量，对高度没有任何限制，那么测量出来的就是完全展示内容所需要的高度
        val secondHeightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        super.onMeasure(widthMeasureSpec, secondHeightMeasureSpec)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return false
        mVelocityTracker.addMovement(event)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> lastY = event.rawY.roundToInt()
            MotionEvent.ACTION_MOVE -> {
                val y = event.rawY.roundToInt()
                val dy = y - lastY
                lastY = y
                if (hasCanScrollParent(dy)) {
                    //获取滑动距离
                    val remain = dy - consumed[1]
                    if (remain != 0) {
                        scrollBy(0, -remain)
                    }
                } else {
                    scrollBy(0, -dy)
                }
            }
            MotionEvent.ACTION_UP -> {
                stopNestedScroll()
//                mVelocityTracker.computeCurrentVelocity(1000)//该参数指定的是1S内滑动的像素。也可以指定最大速率
//                val myScrollFly = mVelocityTracker.yVelocity
            }
        }
        logCurrentVelocityY()
        return true
    }

    private fun logCurrentVelocityY() {
        mVelocityTracker.computeCurrentVelocity(1000)//该参数指定的是1S内滑动的像素。也可以指定最大速率
        val myScrollFly = mVelocityTracker.yVelocity
        LogUtil.i("yVelocity:${myScrollFly}")
    }

    //限制滚动范围
    override fun scrollTo(x: Int, y: Int) {
        val maxY = measuredHeight - showHeight
        var theY: Int = y
        if (theY > maxY) {
            theY = maxY
        }

        if (theY < 0) {
            theY = 0
        }
        super.scrollTo(x, theY)
    }

    private fun hasCanScrollParent(dy: Int) =
        startNestedScroll(ViewCompat.SCROLL_AXIS_HORIZONTAL) && dispatchNestedPreScroll(
            0,
            dy,
            consumed,
            offset
        )

    override fun setNestedScrollingEnabled(enabled: Boolean) {
        mNestedScrollingChildHelper.isNestedScrollingEnabled = enabled
    }

    override fun isNestedScrollingEnabled(): Boolean {
        return mNestedScrollingChildHelper.isNestedScrollingEnabled
    }

    override fun startNestedScroll(axes: Int): Boolean {
        return mNestedScrollingChildHelper.startNestedScroll(axes)
    }

    override fun stopNestedScroll() {
        mNestedScrollingChildHelper.stopNestedScroll()
    }

    override fun hasNestedScrollingParent(): Boolean {
        return mNestedScrollingChildHelper.hasNestedScrollingParent()
    }

    override fun dispatchNestedScroll(
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        offsetInWindow: IntArray?
    ): Boolean {
        return mNestedScrollingChildHelper.dispatchNestedScroll(
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            offsetInWindow
        )
    }

    override fun dispatchNestedPreScroll(
        dx: Int,
        dy: Int,
        consumed: IntArray?,
        offsetInWindow: IntArray?
    ): Boolean {
        return mNestedScrollingChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow)
    }


    override fun dispatchNestedFling(
        velocityX: Float,
        velocityY: Float,
        consumed: Boolean
    ): Boolean {
        return mNestedScrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed)
    }

    override fun dispatchNestedPreFling(velocityX: Float, velocityY: Float): Boolean {
        return mNestedScrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY)
    }

//    private fun fling() {
//        mNestedScrollingChildHelper.dispatchNestedFling()
//    }
}