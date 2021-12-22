package me.sunbird.javatest;

/**
 * 作者：王豪
 * 日期：2021/12/11
 * 描述：<必填>
 */

class VolitaleTest {
    private static VolitaleTest INSTANCE = null;

    private VolitaleTest() {
    }

    public static VolitaleTest getInstance() {
        if (INSTANCE == null) {
            synchronized (VolitaleTest.class) {
                if (INSTANCE == null) {
                    INSTANCE = new VolitaleTest();
                }
            }
        }
        return INSTANCE;
    }
}
