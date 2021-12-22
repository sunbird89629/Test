package me.sunbird.javatest;

import java.util.Vector;

/**
 * 作者：王豪
 * 日期：2021/12/11
 * 描述：<必填>
 */

class VectorTest {
    private static Vector<Integer> items = new Vector<>();

    public static void main(String[] args) {
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i));
        }
    }
}
