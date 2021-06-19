package com.sunbird.test.sharedflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * 作者：王豪
 * 日期：2021/6/19
 * 描述：<必填>
 */
class LatestNewViewModel(private val newsRepository: NewRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(LatestNewsUiState.Success(emptyList()))
    val uiState: StateFlow<LatestNewsUiState> = _uiState

    init {
        viewModelScope
    }
}

sealed class LatestNewsUiState {
    data class Success(val news: List<ArticleHeadLine>) : LatestNewsUiState()
    data class Error(val exception: Throwable) : LatestNewsUiState()
}

class ArticleHeadLine {

}

class NewRepository {

}
