package me.sunbird.javatest;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 作者：王豪
 * 日期：2021/12/10
 * 描述：<必填>
 */

class ReentrantLockTest extends Thread {
    public static ReentrantLock lock = new ReentrantLock();
    public static int count = 0;

    public ReentrantLockTest(String name) {
        setName(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000000; i++) {
            lock.lock();
            try {
                count++;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        ReentrantLock lock = new ReentrantLock();
//        Condition c1 = lock.newCondition();
//        lock.newCondition();
//
//
//        try {
//            lock.lock();
//            c1.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            lock.unlock();
//        }

        ReentrantLockTest test1 = new ReentrantLockTest("thread1");
        ReentrantLockTest test2 = new ReentrantLockTest("thread2");
        test1.start();
        test2.start();
        test1.join();
        test2.join();
        System.out.println("count:" + count);
    }


}