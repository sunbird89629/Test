package com.sunbird.test.layoutmanager

import android.graphics.PointF

/**
 * 作者：王豪
 * 日期：2021/5/22
 * 描述：<必填>
 */
class PosTan(
    /**
     * 在路径上的位置（百分比）
     */
    var fraction: Float = 0f,
    /**
     * Iitem所对应的索引
     */
    var index: Int = 0,
    /**
     * Item的旋转角度
     */
    var angle: Float = 0f,
    x: Float = 0f,
    y: Float = 0f
) : PointF(x, y) {

    constructor(posTan: PosTan, index: Int, fraction: Float) : this(
        fraction = fraction,
        index = index,
        angle = posTan.angle,
        x = posTan.x,
        y = posTan.y
    )

    fun set(x: Float, y: Float, angle: Float) {
        this.x = x
        this.y = y
    }

    override fun equals(other: Any?): Boolean {
        return other != null && other is PosTan && other.index == index
    }
}