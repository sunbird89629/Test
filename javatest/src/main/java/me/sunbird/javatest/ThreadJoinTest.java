package me.sunbird.javatest;

/**
 * 作者：王豪
 * 日期：2021/12/10
 * 描述：<必填>
 */

class ThreadJoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t=new Thread(){
            @Override
            public void run() {
                super.run();
            }
        };

        t.join();
    }
}
