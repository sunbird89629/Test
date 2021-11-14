package com.sunbird.test

import okio.*
import okio.ByteString.Companion.decodeBase64
import okio.ByteString.Companion.decodeHex
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Throws(IOException::class)
    private fun serialize(o: Any): ByteString {
        val buffer = Buffer()
        ObjectOutputStream(buffer.outputStream()).use {
            it.writeObject(o)
        }
        return buffer.readByteString()
    }


    val goldenValue =
        "rO0ABXNyACljb20uc3VuYmlyZC50ZXN0LkV4YW1wbGVVbml0VGVzdCRUZXN0RGF0YUf7A8RCQe0KAgACSQADYWdlTAAEbmFtZXQAEkxqYXZhL2xhbmcvU3RyaW5nO3hwAAAAHnQADHRoaXNfaXNfbmFtZQ=="

    @Test
    fun testSerialize() {
        val testData = TestData("this_is_name", 30)
        val seriResult = serialize(testData)
        println(seriResult.base64())
    }

    @Test
    fun testDeserialize() {
        val goldenBytes = goldenValue.decodeBase64() ?: return
        val buffer = Buffer()
        buffer.write(goldenBytes)
        ObjectInputStream(buffer.inputStream()).use {
            val result = it.readObject() as TestData
            println(result)
        }
    }

    data class TestData(
        val name: String,
        val age: Int
    ) : Serializable


    @Test
    fun testBit() {
        val a = 256
        val byteString = Buffer().writeInt(a).readByteString().toByteArray().toString()
        val test = a shl 16
        a.toByte()
    }


    @Test
    fun decodePng() {
//        var inputStream = null
//        val PNG_HEADER = "89504e470d0a1a0a".decodeHex()
//        val pngSource = inputStream.source().buffer()
//        val header = pngSource.readByteString(PNG_HEADER.size.toLong())
//        if (header == PNG_HEADER) {
//            println("Is A PNG")
//        } else {
//            println("Not A PNG")
//        }
    }
}