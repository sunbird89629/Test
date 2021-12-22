package me.sunbird.javatest;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 作者：王豪
 * 日期：2021/12/12
 * 描述：<必填>
 */

class ReadWriteMap<K, V> {
    private final Map<K, V> map;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock r = lock.readLock();
    private final Lock w = lock.writeLock();

    public ReadWriteMap() {
        this.map = new HashMap<>();
    }

    public V put(K key, V value) {
        w.lock();
        try {
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return map.put(key, value);
        } finally {
            w.unlock();
        }
    }

    public V get(K key) {
        r.lock();
        try {
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return map.get(key);
        } finally {
            r.unlock();
        }
    }

    public void logThreadInfo() {
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteMap<String, String> map = new ReadWriteMap<>();
        Thread[] threads = new Thread[12];
        for (int i = 0; i < 10; i++) {
            threads[i] = new ReadThread("ReadThread" + i, map);
        }

        for (int i = 10; i < 12; i++) {
            threads[i] = new WriteThread("WriteThread" + i, map);
        }

        for (Thread thread : threads) {
            thread.start();
//            thread.join();
        }

//        while (true) {
//        }

//        System.out.println("done");
    }

    static class ReadThread extends Thread {
        private ReadWriteMap<String, String> map;

        public ReadThread(String name, ReadWriteMap<String, String> map) {
            setName(name);
            this.map = map;
        }

        @Override
        public void run() {
            System.out.println(getName() + " thread start.");
            while (!interrupted()) {
                String value = map.get("key");
                System.out.println(getName() + " read:value is " + value);
            }
        }
    }

    static class WriteThread extends Thread {
        private ReadWriteMap<String, String> map;

        public WriteThread(String name, ReadWriteMap<String, String> map) {
            setName(name);
            this.map = map;
        }

        @Override
        public void run() {
            System.out.println(getName() + " thread start.");
            while (!interrupted()) {
                map.put("key", "test value");
                System.out.println(getName() + " write:value is " + "test value");
            }
        }
    }
}
