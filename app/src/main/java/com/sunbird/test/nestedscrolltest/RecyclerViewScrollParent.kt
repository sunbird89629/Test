package com.sunbird.test.nestedscrolltest

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.NestedScrollingParent

/**
 * 作者：王豪
 * 日期：2021/5/17
 * 描述：<必填>
 */
class RecyclerViewScrollParent : LinearLayoutCompat, NestedScrollingParent {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}