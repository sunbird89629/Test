package com.sunbird.test.javaconsole;

/**
 * 作者：王豪
 * 日期：2021/6/14
 * 描述：<必填>
 */

class HotSwapClassLoader extends ClassLoader {
    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }
    
    public Class loadByte(byte[] classByte) {
        return defineClass(null, classByte, 0, classByte.length);
    }
}
