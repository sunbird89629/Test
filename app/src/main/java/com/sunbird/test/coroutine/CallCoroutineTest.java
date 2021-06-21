package com.sunbird.test.coroutine;

import org.jetbrains.annotations.NotNull;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

/**
 * 作者：王豪
 * 日期：2021/6/20
 * 描述：<必填>
 */

class CallCoroutineTest {
    public static void main(String[] args) {
        Object result = CoroutineTestKt.notSuspend(new Continuation<Integer>() {
            @NotNull
            @Override
            public CoroutineContext getContext() {
                System.out.println("my continuation getContext() called...");
                return EmptyCoroutineContext.INSTANCE;
            }

            @Override
            public void resumeWith(@NotNull Object o) {
                System.out.println("my continuation resumeWith with Object o:" + o);
            }
        });

        System.out.println(result);
    }
}
