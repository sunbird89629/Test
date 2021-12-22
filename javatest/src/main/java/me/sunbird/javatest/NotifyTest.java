package me.sunbird.javatest;

import java.util.LinkedList;
import java.util.List;

/**
 * 作者：王豪
 * 日期：2021/12/11
 * 描述：<必填>
 */

class NotifyTest {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new LinkedList<>();
        Thread r = new Thread(new ReadTask(list));
        Thread w = new Thread(new WriteTask(list));
        r.start();
        w.start();
        r.join();
        w.join();
    }

    static class ReadTask implements Runnable {
        private List<String> list;

        public ReadTask(List<String> list) {
            this.list = list;
        }

        @Override
        public void run() {
            System.out.println("ReadList begin at " + System.currentTimeMillis());
            synchronized (list) {
                try {
                    Thread.sleep(1000);
                    System.out.println("list.wait() begin at " + System.currentTimeMillis());
                    list.wait();
                    System.out.println("list.wait() end at " + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("ReadList end at " + System.currentTimeMillis());
        }
    }

    static class WriteTask implements Runnable {
        private List<String> list;

        public WriteTask(List<String> list) {
            this.list = list;
        }

        @Override
        public void run() {
            System.out.println("WriteList begin at " + System.currentTimeMillis());
            synchronized (list) {
                System.out.println("get lock at " + System.currentTimeMillis());
                list.notify();
                System.out.println("list.notify() at " + System.currentTimeMillis());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("get out of block at " + System.currentTimeMillis());
            }
            System.out.println("WriteList end at " + System.currentTimeMillis());
        }
    }
}
