package com.sunbird.test.paging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sunbird.test.databinding.ActivityPagingTestBinding

class PagingTestActivity : AppCompatActivity() {

    private val mBinding: ActivityPagingTestBinding by lazy {
        ActivityPagingTestBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
    }
}