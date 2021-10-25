package com.sunbird.test.tint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import com.sunbird.test.R

class TintTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tint_test)
        findViewById<LinearLayoutCompat>(R.id.ll_test).backgroundTintList =
            getColorStateList(R.color.light_blue_400)
    }
}