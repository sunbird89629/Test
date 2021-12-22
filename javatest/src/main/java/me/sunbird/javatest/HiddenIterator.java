package me.sunbird.javatest;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 作者：王豪
 * 日期：2021/12/6
 * 描述：<必填>
 */

class HiddenIterator {
    static{
        System.out.println("here is second static init block");
    }
    static {
        System.out.println("here is first static init block");
    }

    {
        System.out.println("here is init block");
    }

    private final Set<Integer> set = new HashSet<>();

    public synchronized void add(Integer i) {
        set.add(i);
    }

    public synchronized void remove(Integer i) {
        set.remove(i);
    }

    public void addTenThings() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            add(random.nextInt());
        }
        synchronized (this) {
            System.out.println("DEBUG: added ten elements to" + set);
        }
    }

    public static void main(String[] args) {
        HiddenIterator hiddenIterator = new HiddenIterator();
        for (int i = 0; i < 1000; i++) {
            new Thread() {
                @Override
                public void run() {
                    hiddenIterator.addTenThings();
                }
            }.start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
