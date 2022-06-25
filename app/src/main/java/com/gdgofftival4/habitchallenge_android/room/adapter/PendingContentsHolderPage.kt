package com.gdgofftival4.habitchallenge_android.room.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.gdgofftival4.habitchallenge_android.databinding.ItemPendingContentsBinding
import com.gdgofftival4.habitchallenge_android.room.model.RecordResponse


class PendingContentsHolderPage internal constructor(
    itemView: View,
    val onClick: (postId: String) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemPendingContentsBinding.bind(itemView)

    var data: RecordResponse? = null
    fun onBind(data: RecordResponse) {
        this.data = data
        itemView.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onClick.invoke(data.post_id)
            }
        }

        Glide.with(itemView.context)
            .load(data.post_image_url)
            .transform(CenterCrop())
            .into(binding.contentImg)
    }
}
