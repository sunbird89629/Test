package me.sunbird.javatest;

/**
 * 作者：王豪
 * 日期：2021/12/6
 * 描述：<必填>
 */

class TestConcurrentModification {
    public int test = 0;

    public static void main(String[] args) {
        TestConcurrentModification modification = new TestConcurrentModification();
        modification.test = 5;
//        for (int i = 0; i < 100; i++) {
//            System.out.println(i);
//        }
    }
}
