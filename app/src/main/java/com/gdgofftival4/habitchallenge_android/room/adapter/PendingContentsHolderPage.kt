package com.gdgofftival4.habitchallenge_android.room.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.gdgofftival4.habitchallenge_android.databinding.ItemPendingContentsBinding
import com.gdgofftival4.habitchallenge_android.databinding.ItemRankBinding
import com.gdgofftival4.habitchallenge_android.databinding.ItemRoomBinding
import com.gdgofftival4.habitchallenge_android.home.model.Room
import com.gdgofftival4.habitchallenge_android.room.model.PendingContent
import com.gdgofftival4.habitchallenge_android.room.model.Rank


class PendingContentsHolderPage internal constructor(
    itemView: View,
    val onClick: (position: Int) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemPendingContentsBinding.bind(itemView)

    var data: PendingContent? = null
    fun onBind(data: PendingContent) {
        this.data = data
        itemView.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onClick.invoke(position)
            }
        }
        Glide.with(itemView.context)
            .load(data.contentImg)
            .transform(CenterCrop())
            .into(binding.contentsImg)
    }
}
