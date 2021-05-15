package com.sunbird.test

import android.content.Context
import android.util.AttributeSet
import android.view.View
import org.xutils.common.util.LogUtil

/**
 * 作者：王豪
 * 日期：2021/5/15
 * 描述：<必填>
 */
class TestView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        LogUtil.d("called......")
    }
}