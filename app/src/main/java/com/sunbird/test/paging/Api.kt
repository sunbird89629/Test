package com.sunbird.test.paging

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 作者：王豪
 * 日期：2021/5/28
 * 描述：<必填>
 */
interface Api {

    /**
     * 获取影院当前上映的电影
     */
    @GET("movie/in_theaters")
    fun getMovies(
        @Query("start") since: Int,
        @Query("count") perPage: Int
    ): Call<Movies>
}