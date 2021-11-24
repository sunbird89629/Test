package com.sunbird.test.timeline

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * 作者：王豪
 * 日期：2021/11/18
 * 描述：<必填>
 */
class TimelineDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(120, 120, 120, 120)
    }
}