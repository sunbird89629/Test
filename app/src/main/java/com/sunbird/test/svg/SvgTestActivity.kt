package com.sunbird.test.svg

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sunbird.test.R

class SvgTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_svg_test)
//        resources.getDrawable(R.drawable.ic_chain_svg_test, null)
    }

    fun test() {
        val consumer: (String) -> Unit = {
            println(it)
        }
        consumer.invoke("test")
    }
}