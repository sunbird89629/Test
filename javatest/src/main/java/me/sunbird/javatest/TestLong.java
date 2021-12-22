package me.sunbird.javatest;

/**
 * 作者：王豪
 * 日期：2021/12/7
 * 描述：<必填>
 */

class TestLong {
    public static void main(String[] args) {
//        long a = Long.MAX_VALUE;
//        a = a + 1;
//        System.out.println(a);
//        synchronized (new Object()) {
//            System.out.println("test");
//        }

        try {
            Thread.currentThread().wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void test() {
        try {
            System.out.println("try block");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally block");
        }
    }
}
