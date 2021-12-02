package com.sunbird.test

import org.junit.Test

/**
 * 作者：王豪
 * 日期：2021/11/25
 * 描述：<必填>
 */

@JvmInline
value class User(val name: String) {
    fun printUserName() {
        println(name)
    }
}


class KotlinClassesTest {

    @Test
    fun testValueClass() {
        val user = User("sunbird")
        val name = user.name
        user.printUserName()
    }
}