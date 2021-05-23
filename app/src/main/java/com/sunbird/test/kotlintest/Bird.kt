package com.sunbird.test.kotlintest

/**
 * 作者：王豪
 * 日期：2021/5/22
 * 描述：<必填>
 */
data class Bird(
    var weight: Double,
    var age: Int,
    var color: String
)

class Prize private constructor(val name: String, val count: Int, val type: Int) {
    companion object {
        val TYPE_COMMON = 1
        val TYPE_REDPACK = 2
        val TYPE_COUPIN = 3
        val defaultCommonPrize = Prize("普通奖品", 10, TYPE_COMMON)

        fun newRedpackPrice(name: String, count: Int) = Prize(name, count, TYPE_REDPACK)

        fun defaultCommonPrize() = defaultCommonPrize
    }
}