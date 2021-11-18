package com.sunbird.test.recyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.sunbird.test.databinding.ActivityRecyclerViewTestBinding

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