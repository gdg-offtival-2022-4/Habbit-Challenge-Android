package com.gdgofftival4.habitchallenge_android.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.gdgofftival4.habitchallenge_android.databinding.ItemRoomBinding
import com.gdgofftival4.habitchallenge_android.home.model.Room


class RoomHolderPage internal constructor(
    itemView: View,
    val onClick: (position: Int) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemRoomBinding.bind(itemView)

    var data: Room? = null
    fun onBind(data: Room) {
        this.data = data
        binding.itemRoom.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onClick.invoke(position)
            }
        }
        Glide.with(itemView.context)
            .load(data.roomImg)
            .transform(CenterCrop())
            .into(binding.roomImage)

        Glide.with(itemView.context)
            .load(data.roomImg)
            .transform(CenterCrop(), RoundedCorners(200))
            .into(binding.userOne)

        Glide.with(itemView.context)
            .load(data.roomImg)
            .transform(CenterCrop(), RoundedCorners(200))
            .into(binding.userTwo)

        Glide.with(itemView.context)
            .load(data.roomImg)
            .transform(CenterCrop(), RoundedCorners(200))
            .into(binding.userThree)
    }
}
