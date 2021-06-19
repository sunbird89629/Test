package com.sunbird.test.jvm;

/**
 * 作者：王豪
 * 日期：2021/6/14
 * 描述：<必填>
 */


class GrandFather {
    void thinking() {
        System.out.println("I'm grand parent");
    }
}

class Father extends GrandFather {
    @Override
    void thinking() {
        System.out.println("I'm father");
    }
}


class Son extends Father {
    @Override
    void thinking() {
//        try {
//            MethodType methodType = MethodType.methodType(void.class);
//            MethodHandle mh = lookup()
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
