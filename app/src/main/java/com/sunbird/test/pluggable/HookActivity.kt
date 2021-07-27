package com.sunbird.test.pluggable

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.sunbird.test.R


class HookActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    mockActivityTaskManager()

    //    mockInstrumentation()

    setContentView(R.layout.activity_hook)

    
    findViewById<Button>(R.id.btn_launch).setOnClickListener {
      startActivity(Intent(this, SecondHookActivity::class.java))
    }
  }

  private fun mockActivityTaskManager() {
    HookHelper.hookActivityTaskManager()
  }

  private fun mockInstrumentation() {
    val mInstrumentation =
      RefInvoke.getFieldObject(Activity::class.java, this, "mInstrumentation") as Instrumentation
    val evilInstrumentation = EvilInstrumentation(mInstrumentation)

    RefInvoke.setFieldObject(Activity::class.java, this, "mInstrumentation", evilInstrumentation)
  }
}