package com.sunbird.test.glide

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.sunbird.cryptobot.util.OKLog
import com.sunbird.test.databinding.ActivityGlideTestBinding

class GlideTestActivity : AppCompatActivity() {

    companion object {
        const val TEST_IMAGE_URL =
            "https://camo.githubusercontent.com/ac6d55e6df8d655414cf70ffb2ff2ef6e74a26b53329162e440be167c9d60f16/68747470733a2f2f696d616765732e7869616f7a6875616e6c616e2e636f6d2f70686f746f2f323032312f31666333663363656662316438653637353564656135356163383238383734642e706e67"
    }

    private val mBinding by lazy {
        ActivityGlideTestBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        Glide.with(mBinding.ivTest).load(TEST_IMAGE_URL).into(mBinding.ivTest)
        OKLog.i("called......")
    }
}