package com.sunbird.test.coroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sunbird.test.R
import com.sunbird.test.databinding.ActivityCoroutineTestBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.xutils.common.util.LogUtil

class CoroutineTestActivity : AppCompatActivity() {

    private val mBinding by lazy {
        ActivityCoroutineTestBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_test)
        GlobalScope.launch(Dispatchers.Main) {
            for (i in 10 downTo 1) {
                mBinding.tvTest.text = "Countdown ${i}..."
                LogUtil.i("Countdown ${i}-->current thread name:${Thread.currentThread().name}")
                delay(1000)
            }
            mBinding.tvTest.text = "Done!"
        }
        LogUtil.i("onCreated end--->current thread name:${Thread.currentThread().name}")
    }
}