package com.sunbird.test.pluggable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sunbird.test.R

class SecondHookActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_second_hook)
  }
}