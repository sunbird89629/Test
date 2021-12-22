package me.sunbird.javatest.concurrent;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者：王豪
 * 日期：2021/12/5
 * 描述：<必填>
 */

class Holder {
    private int n;

    class Number {
        //number 值
        private int number = 0;
        //doubleNumber 的值为number值得2倍
        private int doubleNumber = 0;

        // doubleNumber 的值为 number 的二倍即为我们设计这个类的需求，这就是这个类的不变形条件


        //在并发条件下调用同一个对象的该方法，可能导致 doubleNumber 的值不为 number 值的二倍
        //即为破坏了该对象的不变性条件
        private void update(int number) {
            this.number = number;
            this.doubleNumber = number * 2;
        }

        //在并发条件下调用同一个对象的加了同步的方法会保证 doubleNumber 为 number 的2倍
        //即为保证该对象的不变性条件
        public synchronized void synchronizedUpdate(int number) {
            this.number = number;
            this.doubleNumber = number * 2;
        }
    }

    public Holder(int n) {

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.n = n;
    }

    public void assertSanity() {
        if (n != n) {
            throw new AssertionError("this statement is false.");
        }
    }


    public static void main(String[] args) {
//        Holder holder = null;
//        new Thread() {
//            @Override
//            public void run() {
//                while (true) {
//                    if (holder != null) {
//                        holder.assertSanity();
//                    }
//                }
//            }
//        }.start();
//        holder = new Holder(1);
        Map<String, Date> lastLogin = Collections.synchronizedMap(new HashMap<>());
        lastLogin.put("test", new Date());
        lastLogin.put("test", new Date());
    }
}