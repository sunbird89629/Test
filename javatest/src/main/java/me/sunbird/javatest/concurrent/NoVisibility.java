package me.sunbird.javatest.concurrent;

/**
 * 作者：王豪
 * 日期：2021/12/5
 * 描述：<必填>
 */

class NoVisibility {
    private static int number = 0;
    private static boolean ready = false;

    static class ReadThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                System.out.println("not ready,do yield.");
                Thread.yield();
            }
            System.out.println("number:" + number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadThread readThread = new ReadThread();
        readThread.start();
        Thread.sleep(1000);
        number = 42;
        ready = true;
        readThread.join();
    }
}
