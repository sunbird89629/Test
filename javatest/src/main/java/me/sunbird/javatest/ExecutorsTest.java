package me.sunbird.javatest;

import java.util.concurrent.SynchronousQueue;

/**
 * 作者：王豪
 * 日期：2021/12/11
 * 描述：<必填>
 */

class ExecutorsTest {
    public static void main(String[] args) throws InterruptedException {
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.submit(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("run called.....");
//            }
//        });
//
//        executorService.shutdown();

        SynchronousQueue<Integer> testQueue = new SynchronousQueue<>();
        Thread producerThread = new ProducerThread(testQueue);
        Thread consumeThread = new ConsumeThread(testQueue);

        producerThread.start();
        consumeThread.start();

        Thread.sleep(10000);

        producerThread.interrupt();
        consumeThread.interrupt();

        System.out.println("done....");

    }

    static class ProducerThread extends Thread {
        private SynchronousQueue<Integer> testQueue;
        private int currentValue = 0;

        public ProducerThread(SynchronousQueue<Integer> testQueue) {
            this.testQueue = testQueue;
        }

        @Override
        public void run() {
            try {
                while (!interrupted()) {
                    System.out.println("product a value:" + currentValue);
                    testQueue.put(currentValue++);
                    Thread.sleep(500);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ConsumeThread extends Thread {
        private SynchronousQueue<Integer> testQueue;

        public ConsumeThread(SynchronousQueue<Integer> testQueue) {
            this.testQueue = testQueue;
        }

        @Override
        public void run() {
            try {
                while (!interrupted()) {
                    int value = testQueue.take();
                    System.out.println("consume a value:" + value);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
