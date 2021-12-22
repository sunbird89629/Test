package me.sunbird.javatest;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 作者：王豪
 * 日期：2021/12/6
 * 描述：<必填>
 */

class TestSync {


    ReentrantLock lock = new ReentrantLock();

    public void testMethod() {
        int a = 3;
//        int b = 4;
        int c = a * 4;
//        synchronized (TestSync.class) {
//
//            System.out.println("test");
//        }
//

//        new TestSync();


        //aload_
        //getfield
        //
//       lock.lock();
//
//        lock.unlock();
//
//        return 0;


//        ed(1,2);
//        B b = new B("6666");

//        B b = new B("6666");
    }


     synchronized static void ed(int a, int b) {
        if (a == 128) {
            int r = 1;
        } else {
            int t = 6;
            String s = "55555";
            s.length();
        }
    }
}


class B {
    public B(String s) {

    }
}