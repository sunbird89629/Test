package com.sunbird.test.nestedscrolltest

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.NestedScrollingParent
import androidx.core.view.NestedScrollingParentHelper

/**
 * 作者：王豪
 * 日期：2021/5/16
 * 描述：<必填>
 */
class MyNestedScrollParent : LinearLayoutCompat, NestedScrollingParent {

    val img: ImageView by lazy {
        getChildAt(0) as ImageView
    }

    val tv: TextView by lazy {
        getChildAt(1) as TextView
    }

    val myNestedScrollChild: MyNestedScrollChild by lazy {
        getChildAt(2) as MyNestedScrollChild
    }

    private val mNestedScrollingParentHelper: NestedScrollingParentHelper by lazy {
        NestedScrollingParentHelper(this)
    }

    private var imgHeight: Int = -1

    private var tvHeight: Int = -1

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onFinishInflate() {
        super.onFinishInflate()

        img.viewTreeObserver.addOnGlobalLayoutListener {
            if (imgHeight <= 0) {
                imgHeight = img.measuredHeight
            }
        }

        tv.viewTreeObserver.addOnGlobalLayoutListener {
            if (tvHeight <= 0) {
                tvHeight = tv.measuredHeight
            }
        }
    }

    override fun onStartNestedScroll(child: View, target: View, nestedScrollAxes: Int): Boolean {
        return target is MyNestedScrollChild
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int) {
        mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, nestedScrollAxes)
    }

    override fun onStopNestedScroll(child: View) {
        mNestedScrollingParentHelper.onStopNestedScroll(child)
    }


    //先于child滚动
    //前3个为输入参数，最后一个是输出参数
    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray) {
        if (showImg(dy) || hideImage(dy)) {
            scrollBy(0, -dy)//滚动
            consumed[1] = dy//告诉child我消费了多少s
        }
    }


    //后于child滚动
    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int
    ) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
    }

    //返回值：是否消费了fling
    override fun onNestedPreFling(target: View, velocityX: Float, velocityY: Float): Boolean {
        return false
    }

    //返回值：是否消费了fling
    override fun onNestedFling(
        target: View,
        velocityX: Float,
        velocityY: Float,
        consumed: Boolean
    ): Boolean {
        return false
    }


    //下拉的时候是否要向下滚动以显示图片
    fun showImg(dy: Int): Boolean {
        if (dy > 0) {
            if (scrollY > 0 && myNestedScrollChild.scrollY == 0) {
                return true
            }
        }

        return false
    }


    //上拉的时候，是否要向上滚动，隐藏图片
    fun hideImage(dy: Int): Boolean {
        if (dy < 0) {
            if (scrollY < imgHeight) {
                return true
            }
        }
        return false
    }


    //scrollBy内部会调用scrollTo
    //限制滚动范围
    override fun scrollTo(x: Int, y: Int) {
        var theY = y;
        if (theY < 0) {
            theY = 0;
        }
        if (theY > imgHeight) {
            theY = imgHeight
        }
        super.scrollTo(x, theY)
    }

}