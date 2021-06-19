package com.sunbird.test.sharedflow

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

/**
 * 作者：王豪
 * 日期：2021/6/19
 * 描述：<必填>
 */
class SharedFlowViewModel(context: Application) : AndroidViewModel(context) {
    private val _ratings = MutableSharedFlow<Ratings>(replay = 1)

    val ratings by lazy {
        fetchRatings()
        _ratings.asSharedFlow()
    }

    private fun fetchRatings() {

    }
}

class Ratings {

}