package com.sunbird.test.svg

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.sunbird.test.R

class SvgAnimTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_svg_anim_test)
        with(findViewById<ImageView>(R.id.iv_anim)) {
            setOnClickListener {
                val drawable = this.drawable
                if (drawable is Animatable) {
                    drawable.start()
                }
            }
        }
    }
}