package me.sunbird.javatest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 作者：王豪
 * 日期：2021/11/6
 * 描述：<必填>
 */

class TestVolatile {
    public AtomicInteger inc = new AtomicInteger(0);
    public volatile int testInt = 10;

    public void increase() {
        inc.getAndIncrement();
    }

    public void testField() {
        int x = 2;
        int y = 0;
        testInt = 20;
        x = 4;
        y = 10;
    }


    public static void main(String[] args) {
        final TestVolatile testVolatile = new TestVolatile();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        testVolatile.increase();
                    }
                }
            }.start();
        }

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        System.out.println(testVolatile.inc);
    }
}
