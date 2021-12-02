package me.sunbird.javatest

/**
 * 作者：王豪
 * 日期：2021/11/26
 * 描述：<必填>
 */


sealed class Figure {
    abstract fun area(): Double
    class Rectangle(var width: Double, var height: Double) : Figure() {
        override fun area(): Double {
            return width * height
        }
    }

    class Circle(var radius: Int) : Figure() {
        override fun area(): Double {
            return Math.PI * radius * radius
        }
    }
}