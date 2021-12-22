package me.sunbird.javatest;

import java.util.concurrent.CountDownLatch;

/**
 * 作者：王豪
 * 日期：2021/12/10
 * 描述：<必填>
 */

class CountDownLaunchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread("Thread:" + i) {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " ready!");
                    latch.countDown();
                    System.out.println(Thread.currentThread().getName() + " ready done!");
                }
            }.start();
        }
//        latch.await();
        Thread.sleep(1000);
        System.out.println("all users ready!!!");
    }
}
