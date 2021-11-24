package com.sunbird.test.timeline

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.sunbird.test.databinding.ItemTimeLineBinding

/**
 * 作者：王豪
 * 日期：2021/11/18
 * 描述：<必填>
 */
class TimelineAdapter : ListAdapter<TimelineItemModel, TimelineItemViewHolder>(TimelineDiffer()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineItemViewHolder {
        val binding = ItemTimeLineBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TimelineItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimelineItemViewHolder, position: Int) {
        holder.model = getItem(position)
        holder.bind()
    }
}

class TimelineItemViewHolder(val binding: ItemTimeLineBinding) :
    RecyclerView.ViewHolder(binding.root) {
    lateinit var model: TimelineItemModel
    fun bind() {
        if (::model.isInitialized) {
            Glide.with(itemView.context)
                .asBitmap()
                .load(model.url)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        binding.ivImage.setImageBitmap(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }
                })
        }
    }
}

class TimelineDiffer : DiffUtil.ItemCallback<TimelineItemModel>() {
    override fun areItemsTheSame(oldItem: TimelineItemModel, newItem: TimelineItemModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: TimelineItemModel,
        newItem: TimelineItemModel
    ): Boolean {
        return oldItem.url == newItem.url
    }
}