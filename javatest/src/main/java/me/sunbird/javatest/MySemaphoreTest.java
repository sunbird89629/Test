package me.sunbird.javatest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 作者：王豪
 * 日期：2021/12/11
 * 描述：<必填>
 */

class MySemaphoreTest {
    public static void main(String[] args) {
//        Semaphore semaphore = new Semaphore(2);
        MySemaphore semaphore = new MySemaphore(2);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.submit(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("线程:" + Thread.currentThread().getName() + "获得许可:" + index);
                    TimeUnit.SECONDS.sleep(1);
                    semaphore.release();
                    System.out.println("允许TASK个数: " + semaphore.availablePermits());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
}
