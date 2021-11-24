package com.sunbird.test.recyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.sunbird.test.databinding.ActivityRecyclerViewTestBinding
import com.sunbird.test.setOnItemClickListener

class RecyclerViewTestActivity : AppCompatActivity() {

    private val viewModel by viewModels<RecyclerViewTestViewModel>()
    private val binding by lazy {
        ActivityRecyclerViewTestBinding.inflate(layoutInflater)
    }

    private val headerAdapter by lazy {
        HeaderAdapter()
    }

    private val itemAdapter by lazy {
        ItemAdapter(
            onItemClick = {
                Toast.makeText(
                    this@RecyclerViewTestActivity,
                    it.model.name,
                    Toast.LENGTH_LONG
                ).show()
            }
        )
    }

    private val concatAdapter by lazy {
        ConcatAdapter(headerAdapter, itemAdapter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvItems.apply {
            layoutManager = LinearLayoutManager(
                this@RecyclerViewTestActivity,
                LinearLayoutManager.VERTICAL,
                false
            ).apply {
//                addItemDecoration(
//                    DividerItemDecoration(
//                        this@RecyclerViewTestActivity,
//                        DividerItemDecoration.VERTICAL
//                    )
//                )
            }
            adapter = itemAdapter

            setOnItemClickListener { view, pos ->
                Toast.makeText(
                    this@RecyclerViewTestActivity,
                    "item${pos}clicked",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        viewModel.dataStore.observe(this) {
            itemAdapter.submitList(it)
        }
        binding.btnDoScroll.setOnClickListener {
//            binding.rvItems.smoothScrollToPosition(itemAdapter.itemCount - 1)
//            binding.rvItems.scrollToPosition(itemAdapter.itemCount - 1)
            binding.rvItems.smoothScrollToPositionWithOffset(5, -100)
        }

        binding.btnChangeItem.setOnClickListener {
            viewModel.changeItem(4) {
                it.name = "changed name"
            }
        }
    }
}



嗯，这个方案也挺好的。但我还是想说下我的那个方案。

我举一个例子，假如我有一个PictureActivity，对外提供两个服务，例如：

1.选择手机中的图片（支持多选）
2.可以对提供的图片进行剪切

按照传统的Activity的通信方式，调用者需要进到PictureActivity里，看如何
传递Intent的参数，以及返回结果如何解析（因为我们得知道result key是什么）。
而以本文中提到的方式，我可以把这两个需求封装成PictureActivity companion
中的两个suspend function,例如：
/**
 * 选择图片
 * @param multi:是否支持多选 默认为单选
 * @return List?:用户选择的图片Uri列表
 */
suspend fun selectPicture(multi:Boolean=false):List?{
    .......
}
/**
 * 剪切图片
 * @param pictureUri:要接切的图片uri
 * @param maxWidth:剪切的最大宽度 默认为图片的宽度
 * @param maxHeight:剪切的最大高度 默认为图片的高度
 * @return Uri:剪切后的图片Uri
 */
suspend fun cropPicture(pictureUri:Uri,maxWidth:Int=-1,maxHeight:Int=-1):Uri{
    ......
}

这样我们就把PictureActivity封装成了一个组件，能对外提供的功能全部封装成了一个个见名知
意的方法，用户不需要知道细节，通过方法就知道需要传那些参数，参数的类型是什么，那些是必传的，
那些是可以不传的，返回值的类型等。 而因为封装成了suspend方法，调用者就可以通过协程像调用
一个普通方法一样完成Activity的通信，例如：

GlobalScope.launch {
    val pictures = PictureActivity.selecPicture(multi=true);
    //获取到了图片，就像调用了一个普通方法，接着就可以对选择的图片进行处理了
    。。。。。。
}

这种方式的代码可读性就比较好。 当然，若有我没想道的问题，还忘不吝赐教，谢谢。