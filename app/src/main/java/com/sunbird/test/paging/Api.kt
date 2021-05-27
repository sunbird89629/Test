package com.sunbird.test.paging

import retrofit2.Call

/**
 * 作者：王豪
 * 日期：2021/5/28
 * 描述：<必填>
 */
interface Api {
    fun getMovies: Call<>
}