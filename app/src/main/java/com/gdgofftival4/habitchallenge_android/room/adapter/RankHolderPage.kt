package com.gdgofftival4.habitchallenge_android.room.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.gdgofftival4.habitchallenge_android.databinding.ItemRankBinding
import com.gdgofftival4.habitchallenge_android.databinding.ItemRoomBinding
import com.gdgofftival4.habitchallenge_android.home.model.Room
import com.gdgofftival4.habitchallenge_android.room.model.Rank


class RankHolderPage internal constructor(
    itemView: View,
    val onClick: (position: Int) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemRankBinding.bind(itemView)

    var data: Rank? = null
    fun onBind(data: Rank) {
        this.data = data
        binding.rankMain.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onClick.invoke(position)
            }
        }
        Glide.with(itemView.context)
            .load(data.userImg)
            .transform(CenterCrop())
            .into(binding.userImage)

        binding.userName.text  = data.userName
        binding.userRank.text = "+"+data.userCombo.toString()
    }
}
