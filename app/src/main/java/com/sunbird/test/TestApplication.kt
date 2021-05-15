package com.sunbird.test

import android.app.Application
import org.xutils.x

/**
 * 作者：王豪
 * 日期：2021/5/15
 * 描述：<必填>
 */
class TestApplication : Application {
    constructor() : super()

    override fun onCreate() {
        super.onCreate()
        x.Ext.init(this)
        x.Ext.setDebug(BuildConfig.DEBUG)
    }
}