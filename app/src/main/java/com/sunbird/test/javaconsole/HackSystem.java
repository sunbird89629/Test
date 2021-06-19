package com.sunbird.test.javaconsole;

import java.io.PrintStream;

/**
 * 作者：王豪
 * 日期：2021/6/14
 * 描述：为JavaClass劫持java.lang.System提供支持
 * 除了out和err外，其余的都直接转发给System处理
 */

class HackSystem {

    public static PrintStream out;

    public static void clearBuffer() {

    }

    public static String getBufferString() {
        return null;
    }
}
