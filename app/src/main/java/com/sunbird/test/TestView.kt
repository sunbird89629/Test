package com.sunbird.test

import android.content.Context
import android.view.View

/**
 * 作者：王豪
 * 日期：2021/5/15
 * 描述：<必填>
 */
class TestView:View {
    constructor(context: Context?) : super(context)
    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
    }
}