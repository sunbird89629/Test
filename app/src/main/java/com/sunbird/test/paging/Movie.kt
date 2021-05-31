package com.sunbird.test.paging

/**
 * 作者：王豪
 * 日期：2021/5/28
 * 描述：<必填>
 */
data class Movie(
    val id: String,
    val title: String,
    val year: String,
    val images: Images
) {
    class Images(
        val small: String
    )
}
