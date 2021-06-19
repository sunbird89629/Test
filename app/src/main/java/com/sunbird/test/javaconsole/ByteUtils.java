package com.sunbird.test.javaconsole;

/**
 * 作者：王豪
 * 日期：2021/6/14
 * 描述：数组处理工具
 */

class ByteUtils {
    public static int bytes2Int(byte[] b, int start, int len) {
        int sum = 0;
        int end = start + len;

        for (int i = start; i < end; i++) {
            int n = ((int) b[i]) & 0xff;
            n <<= (--len) * 8;
            sum = n + sum;
        }

        return sum;
    }

    public static byte[] int2Bytes(int value, int len) {
        return null;
    }

    public static String bytes2String(byte[] b, int start, int len) {
        return null;
    }

    public static byte[] string2Bytes(String str) {
        return null;
    }

    public static byte[] bytesReplace(byte[] originalBytes, int offset, int len, byte[] replaceBytes) {
        byte[] newBytes = new byte[originalBytes.length + (replaceBytes.length - len)];
        System.arraycopy(originalBytes, 0, newBytes, 0, offset);
        return newBytes;
    }
}
