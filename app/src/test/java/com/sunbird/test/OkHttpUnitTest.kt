package com.sunbird.test

import okhttp3.*
import okio.ByteString
import org.junit.Test
import java.util.concurrent.TimeUnit


/**
 * 作者：王豪
 * 日期：2021/11/5
 * 描述：<必填>
 */
class OkHttpUnitTest {
    @Test
    fun testOkHttpClient() {
        val client = OkHttpClient()
        val request = Request.Builder().url("https://www.baidu.com").build()
        client.newCall(request).execute().use {
            println(it.body?.string())
//            roof dad square nuclear innocent police
//                    grain true resist life spatial
//                beach
        }
    }


    @Test
    fun testOkHttpWebSocket() {
        val mClient: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(3, TimeUnit.SECONDS) //设置读取超时时间
            .writeTimeout(3, TimeUnit.SECONDS) //设置写的超时时间
            .connectTimeout(3, TimeUnit.SECONDS) //设置连接超时时间
            .build()

        val url = "ws://121.40.165.18:8800"
        val request = Request.Builder().get().url(url).build()

        val webSocket = mClient.newWebSocket(request, object : WebSocketListener() {
            /**
             * Invoked when both peers have indicated that no more messages will be transmitted and the
             * connection has been successfully released. No further calls to this listener will be made.
             */
            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosed(webSocket, code, reason)
            }

            /**
             * Invoked when the remote peer has indicated that no more incoming messages will be transmitted.
             */
            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosing(webSocket, code, reason)
            }

            /**
             * Invoked when a web socket has been closed due to an error reading from or writing to the
             * network. Both outgoing and incoming messages may have been lost. No further calls to this
             * listener will be made.
             */
            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
            }

            /** Invoked when a text (type `0x1`) message has been received. */
            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
            }

            /** Invoked when a binary (type `0x2`) message has been received. */
            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                super.onMessage(webSocket, bytes)
            }

            /**
             * Invoked when a web socket has been accepted by the remote peer and may begin transmitting
             * messages.
             */
            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
            }
        })

        while (true) {
            Thread.sleep(100000)
        }
    }

}