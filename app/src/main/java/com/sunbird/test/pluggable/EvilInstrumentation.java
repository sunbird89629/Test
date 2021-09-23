package com.sunbird.test.pluggable;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/**
 * 作者：王豪
 * 日期：2021/7/26
 * 描述：<必填>
 */

class EvilInstrumentation extends Instrumentation {

    private Instrumentation mBase;

    public EvilInstrumentation(Instrumentation mBase) {
        this.mBase = mBase;
    }

    private static final String TAG = EvilInstrumentation.class.getSimpleName();

    public ActivityResult execStartActivity(
            Context who,
            IBinder contextThread,
            IBinder token,
            Activity target,
            Intent intent,
            int requestCode,
            Bundle options
    ) {

        Log.d(TAG, "XXX到此一游!");

        // 开始调用原始的方法, 调不调用随你,但是不调用的话, 所有的startActivity都失效了.
        // 由于这个方法是隐藏的,因此需要使用反射调用;首先找到这个方法
        Class[] p1 = {Context.class, IBinder.class,
                IBinder.class, Activity.class,
                Intent.class, int.class, Bundle.class};
        Object[] v1 = {who, contextThread, token, target,
                intent, requestCode, options};
        return (ActivityResult) RefInvoke.invokeInstanceMethod(
                mBase, "execStartActivity", p1, v1);
    }
}
