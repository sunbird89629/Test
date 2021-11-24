package com.sunbird.test.timeline

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

/**
 * 作者：王豪
 * 日期：2021/11/18
 * 描述：<必填>
 */
class TimelineRecyclerView : RecyclerView {
    private val linePaint by lazy {
        Paint().apply {
            strokeWidth = 6f
        }
    }

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onDrawForeground(canvas: Canvas?) {
        super.onDrawForeground(canvas)
        canvas ?: return
        val x = (width / 2).toFloat()
        canvas.drawLine(x, 0f, x, height.toFloat(), linePaint)
    }
}