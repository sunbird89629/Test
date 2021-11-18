package com.sunbird.test.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sunbird.test.databinding.HeaderItemRecyclerviewBinding

/**
 * 作者：王豪
 * 日期：2021/11/14
 * 描述：<必填>
 */
class HeaderAdapter : RecyclerView.Adapter<HeaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val binding = HeaderItemRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HeaderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = 1
}

class HeaderViewHolder(val binding: HeaderItemRecyclerviewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind() {
    }
}