package com.sunbird.test.timeline

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 作者：王豪
 * 日期：2021/11/18
 * 描述：<必填>
 */
class TimelineViewModel : ViewModel() {
    val timelinesLiveData = MutableLiveData(
        listOf(
            TimelineItemModel(
                0,
                "https://ss1.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/baike/pic/item/0bd162d9f2d3572c00afa5548013632763d0c3ec.jpg"
            ),
            TimelineItemModel(
                1,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171002%2F987bb4c1e0ec4fd5bbf5104ab07921c1.jpeg&refer=http%3A%2F%2F5b0988e595225.cdn.sohucs.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839683&t=739588bcc063564bd70440c7ecdea406"
            ),
            TimelineItemModel(
                2,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fupload-images.jianshu.io%2Fupload_images%2F3028472-33591a149b4c5ad3.png&refer=http%3A%2F%2Fupload-images.jianshu.io&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839683&t=5b5c650a1e79d26b0fd6912f594f0286"
            ),
            TimelineItemModel(
                3,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jiaoyubao.cn%2Fimages%2Fsh%2Fshizhan%2Fsj4.gif&refer=http%3A%2F%2Fimg.jiaoyubao.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839683&t=85f1d790a060d7ac0215eaae044fe504"
            ),
            TimelineItemModel(
                4,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhelp.anzhibao.com%2Fwp-content%2Fuploads%2F20171214%2F151269695941498277.jpg&refer=http%3A%2F%2Fhelp.anzhibao.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839683&t=b10b910ea5d5e140963e876a71a799eb"
            ),
            TimelineItemModel(
                5,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fappimgs01.sj88.com%2Fpic%2F2018%2F01%2F23%2F1572420_20180123095249_6106.jpg&refer=http%3A%2F%2Fappimgs01.sj88.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839812&t=18ef4db3b7b5aa1e2c03ed0b15cc61a7"
            ),
            TimelineItemModel(
                6,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.vjshi.com%2F2017-09-20%2Fdf152a47c9e578a111bd62c6563a4f1c%2F00002.jpg%3Fx-oss-process%3Dstyle%2Fwatermark&refer=http%3A%2F%2Fpic.vjshi.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839812&t=6b1cc8082215006357f151c9deed7da7"
            ),
            TimelineItemModel(
                7,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.wendangwang.com%2Fpic%2Ffa4c28b45499376cb86dce3a%2F1-1209-jpg_6_0_______-840-0-0-840.jpg&refer=http%3A%2F%2Fimg.wendangwang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839820&t=2897a370864cdd232b1ffa71a5345174"
            ),
            TimelineItemModel(
                8,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Fsteins-gate%2F153912629_screenshot.jpg&refer=http%3A%2F%2Fi0.hdslb.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839820&t=2af81532f91677c394b08d81893da38a"
            ),
            TimelineItemModel(
                9,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdl2.iteye.com%2Fupload%2Fattachment%2F0123%2F8229%2F548ca3f3-bc64-3890-9b11-eacb21f55d9a.jpg&refer=http%3A%2F%2Fdl2.iteye.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839820&t=30342c0a21b078c0a63cbbfdc870aaab"
            ),
            TimelineItemModel(
                10,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg1.cache.netease.com%2Fcatchpic%2FC%2FCB%2FCB10E76F7BA443980D534A7846041683.jpg&refer=http%3A%2F%2Fimg1.cache.netease.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839820&t=f3a1798e48999838eed68287aea3db21"
            ),
            TimelineItemModel(
                11,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fbfbb6069c768ccea4dc8ca8f148d011db599cdc654407-TsJ9ey_fw658&refer=http%3A%2F%2Fhbimg.b0.upaiyun.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839945&t=53219dd5999e5da8400f7b83227523b9"
            ),
            TimelineItemModel(
                12,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Ffile.elecfans.com%2Fweb1%2FM00%2FBB%2FD0%2FpIYBAF6qjBuAGc-rAAFgnIR5dR4601.png&refer=http%3A%2F%2Ffile.elecfans.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839945&t=b8c23ed54aa8bb3313c5443c844ba02a"
            ),
            TimelineItemModel(
                13,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimage5.suning.cn%2Fuimg%2Fb2c%2Fnewcatentries%2F0070171823-000000000742513200_4_800x800.jpg&refer=http%3A%2F%2Fimage5.suning.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839945&t=50d21b451d741b501dee9a262a3e7912"
            ),
            TimelineItemModel(
                14,
                "https://img0.baidu.com/it/u=1801600267,948589904&fm=224&fmt=auto&gp=0.jpg"
            ),
            TimelineItemModel(
                15,
                "https://ss1.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/baike/pic/item/0bd162d9f2d3572c00afa5548013632763d0c3ec.jpg"
            ),
            TimelineItemModel(
                16,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171002%2F987bb4c1e0ec4fd5bbf5104ab07921c1.jpeg&refer=http%3A%2F%2F5b0988e595225.cdn.sohucs.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839683&t=739588bcc063564bd70440c7ecdea406"
            ),
            TimelineItemModel(
                17,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fupload-images.jianshu.io%2Fupload_images%2F3028472-33591a149b4c5ad3.png&refer=http%3A%2F%2Fupload-images.jianshu.io&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839683&t=5b5c650a1e79d26b0fd6912f594f0286"
            ),
            TimelineItemModel(
                18,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jiaoyubao.cn%2Fimages%2Fsh%2Fshizhan%2Fsj4.gif&refer=http%3A%2F%2Fimg.jiaoyubao.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839683&t=85f1d790a060d7ac0215eaae044fe504"
            ),
            TimelineItemModel(
                19,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhelp.anzhibao.com%2Fwp-content%2Fuploads%2F20171214%2F151269695941498277.jpg&refer=http%3A%2F%2Fhelp.anzhibao.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839683&t=b10b910ea5d5e140963e876a71a799eb"
            ),
            TimelineItemModel(
                20,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fappimgs01.sj88.com%2Fpic%2F2018%2F01%2F23%2F1572420_20180123095249_6106.jpg&refer=http%3A%2F%2Fappimgs01.sj88.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839812&t=18ef4db3b7b5aa1e2c03ed0b15cc61a7"
            ),
            TimelineItemModel(
                21,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.vjshi.com%2F2017-09-20%2Fdf152a47c9e578a111bd62c6563a4f1c%2F00002.jpg%3Fx-oss-process%3Dstyle%2Fwatermark&refer=http%3A%2F%2Fpic.vjshi.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839812&t=6b1cc8082215006357f151c9deed7da7"
            ),
            TimelineItemModel(
                22,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.wendangwang.com%2Fpic%2Ffa4c28b45499376cb86dce3a%2F1-1209-jpg_6_0_______-840-0-0-840.jpg&refer=http%3A%2F%2Fimg.wendangwang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839820&t=2897a370864cdd232b1ffa71a5345174"
            ),
            TimelineItemModel(
                23,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Fsteins-gate%2F153912629_screenshot.jpg&refer=http%3A%2F%2Fi0.hdslb.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839820&t=2af81532f91677c394b08d81893da38a"
            ),
            TimelineItemModel(
                24,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdl2.iteye.com%2Fupload%2Fattachment%2F0123%2F8229%2F548ca3f3-bc64-3890-9b11-eacb21f55d9a.jpg&refer=http%3A%2F%2Fdl2.iteye.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839820&t=30342c0a21b078c0a63cbbfdc870aaab"
            ),
            TimelineItemModel(
                25,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg1.cache.netease.com%2Fcatchpic%2FC%2FCB%2FCB10E76F7BA443980D534A7846041683.jpg&refer=http%3A%2F%2Fimg1.cache.netease.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839820&t=f3a1798e48999838eed68287aea3db21"
            ),
            TimelineItemModel(
                26,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fbfbb6069c768ccea4dc8ca8f148d011db599cdc654407-TsJ9ey_fw658&refer=http%3A%2F%2Fhbimg.b0.upaiyun.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839945&t=53219dd5999e5da8400f7b83227523b9"
            ),
            TimelineItemModel(
                27,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Ffile.elecfans.com%2Fweb1%2FM00%2FBB%2FD0%2FpIYBAF6qjBuAGc-rAAFgnIR5dR4601.png&refer=http%3A%2F%2Ffile.elecfans.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839945&t=b8c23ed54aa8bb3313c5443c844ba02a"
            ),
            TimelineItemModel(
                28,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimage5.suning.cn%2Fuimg%2Fb2c%2Fnewcatentries%2F0070171823-000000000742513200_4_800x800.jpg&refer=http%3A%2F%2Fimage5.suning.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639839945&t=50d21b451d741b501dee9a262a3e7912"
            ),
            TimelineItemModel(
                29,
                "https://img0.baidu.com/it/u=1801600267,948589904&fm=224&fmt=auto&gp=0.jpg"
            )
        )
    )
}