package me.sunbird.javatest;

import java.util.Random;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 作者：王豪
 * 日期：2021/12/10
 * 描述：<必填>
 */

class MyCountDownLatch {
    private Sync sync;

    public MyCountDownLatch(int count) {
        this.sync = new Sync(count);
    }

    public void countDown() {
        sync.releaseShared(1);
    }


    //这里的 await（） 方法可以设计成相应中断模式，因为 CountDownLatch 释放锁的操作是由其他线程调用的
    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    static class Sync extends AbstractQueuedSynchronizer {
        public Sync(int count) {
            setState(count);
        }

        //当state为0的时候才获取到，不为0就被阻塞
        @Override
        protected int tryAcquireShared(int arg) {
            System.out.println("tryAcquireShared()");
            return getState() <= 0 ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            System.out.println(Thread.currentThread().getName());
            System.out.println("tryReleaseShared()");
            for (; ; ) {
                int state = getState();
                //如果 state 已经小于等于 0 了，说明等待线程已经被唤醒了。就不用这个线程再次唤醒了
                if (state <= 0) return false;
                int nextS = state - arg;

                if (compareAndSetState(state, nextS)) {
                    //这时如果小于等于0，就应该去唤醒等待线程了
                    return nextS <= 0;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        CountDownLatch latch = new CountDownLatch(5);
        MyCountDownLatch latch = new MyCountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            new Thread("Thread-" + i) {
                @Override
                public void run() {
                    Random random = new Random();
                    long sleepTimes = (random.nextInt(4) + 3) * 1000;
                    try {
                        Thread.sleep(sleepTimes);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " ready");
                    latch.countDown();
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

//                    sleepTimes = random.nextInt(4) * 1000;
//                    try {
//                        Thread.sleep(sleepTimes);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }

                    System.out.println(Thread.currentThread().getName() + " done");
                }
            }.start();
        }

//        Thread.sleep(1000000);

        latch.await();

        System.out.println("all work done");
//        latch.await();
    }

}
