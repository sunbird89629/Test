package me.sunbird.javatest


var testValue = 0

fun main() {
    Thread {
        while (true) {
            println("testValue:${testValue}")
        }
    }.start()
    Thread {
        while (true) {
            testValue++
        }
    }.start()
}