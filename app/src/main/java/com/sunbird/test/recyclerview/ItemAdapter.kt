package com.sunbird.test.recyclerview

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sunbird.cryptobot.util.OKLog
import com.sunbird.test.databinding.ItemItemBinding


/**
 * 作者：王豪
 * 日期：2021/11/14
 * 描述：<必填>
 */
class ItemAdapter(val onItemClick: (holder: ItemViewHolder) -> Unit) :
    ListAdapter<ItemModel, ItemViewHolder>(ItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        OKLog.i("called......")
        val binding = ItemItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        OKLog.i("called(position:${position})")
        holder.model = getItem(position)
        holder.bind()
    }

    override fun onViewRecycled(holder: ItemViewHolder) {
        OKLog.i("position:${holder.model.name}")
        super.onViewRecycled(holder)
    }
}

class ItemViewHolder(
    val itemViewBinding: ItemItemBinding,
    val onItemClick: (holder: ItemViewHolder) -> Unit
) :
    RecyclerView.ViewHolder(itemViewBinding.root) {
    lateinit var model: ItemModel

    init {
        itemViewBinding.root.setOnClickListener {
            onItemClick.invoke(this)
        }
    }

    fun bind() {
        if (this::model.isInitialized) {
            itemViewBinding.tvName.text = model.name
        } else {
            itemViewBinding.tvName.text = ""
        }
    }
}

class ItemDiffCallback : DiffUtil.ItemCallback<ItemModel>() {
    override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
        OKLog.i("oldItem:${oldItem},newItem:${newItem}")
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
        OKLog.i("oldItem:${oldItem},newItem:${newItem}")
        return TextUtils.equals(oldItem.name, newItem.name)
    }
}