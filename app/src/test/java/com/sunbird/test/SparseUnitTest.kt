package com.sunbird.test

import android.util.SparseArray
import org.junit.Test

/**
 * 作者：王豪
 * 日期：2021/12/13
 * 描述：<必填>
 */
class SparseUnitTest {
    @Test
    fun testSparseArray() {
        val array = SparseArray<String>()
        for (i in 0..100) {
            array.put(i, "value for $i")
        }
        println("array.size()=${array.size()}")
        assert(array.size() == 101)
    }
}