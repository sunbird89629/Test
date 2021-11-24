package com.sunbird.test.timeline

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sunbird.test.databinding.ActivityTimeLineBinding

class TimelineActivity : AppCompatActivity() {

    private val mBinding by lazy {
        ActivityTimeLineBinding.inflate(layoutInflater)
    }

    private val mViewModel by viewModels<TimelineViewModel>()

    private val mTimelineAdapter by lazy {
        TimelineAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.trvItems.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = mTimelineAdapter
            addItemDecoration(TimelineDecoration())
        }
        mViewModel.timelinesLiveData.observe(this) {
            mTimelineAdapter.submitList(it)
        }
    }
}