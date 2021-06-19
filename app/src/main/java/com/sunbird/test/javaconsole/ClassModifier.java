package com.sunbird.test.javaconsole;

/**
 * 作者：王豪
 * 日期：2021/6/14
 * 描述：修改Class文件，暂时只提供修改常量池常量的功能
 */

class ClassModifier {

    /**
     * Class文件中常量池的起始偏移
     */
    private static final int CONSTANT_ROOT_COUNT_INDEX = 8;

    /**
     * CONSTANT_Utf8_info常量的tag标志
     */
    private static final int CONSTANT_Utf8_info = 1;


    public ClassModifier(byte[] classByte) {

    }

    public byte[] modifyUTF8Constant(String oldStr, String newStr) {
        return null;
    }
}
