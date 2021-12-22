package me.sunbird.javatest;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 作者：王豪
 * 日期：2021/12/11
 * 描述：<必填>
 */

class CyclicBarrierTest {

    public static void main(String[] args) throws InterruptedException {
        int barrierCount = 5;
        CyclicBarrier barrier = new CyclicBarrier(barrierCount, () -> {
            System.out.println("==========================");
        });
        Thread[] threads = new Thread[barrierCount];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread("Thread:" + i) {
                @Override
                public void run() {
                    try {
                        Thread.sleep(getRandomSleepTimes());
                        System.out.println(getName() + " 冲到栅栏A");
                        barrier.await();
                        System.out.println(getName() + " 冲破栅栏A");
                        Thread.sleep(getRandomSleepTimes());
                        System.out.println(getName() + " 冲到栅栏B");
                        barrier.await();
                        System.out.println(getName() + " 冲破栅栏B");
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            };
        }
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }

    public static int getRandomSleepTimes() {
        Random random = new Random();
        return random.nextInt(4000);
    }
}
