package com.sunbird.test.paging

/**
 * 作者：王豪
 * 日期：2021/5/28
 * 描述：<必填>
 */
class Movies(
    val count: Int,
    val start: Int,
    val total: Int
) {
    var movieList: List<Movie>?=null
}
