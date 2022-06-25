package com.gdgofftival4.habitchallenge_android.room.adapter

import android.net.Uri
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.gdgofftival4.habitchallenge_android.R
import com.gdgofftival4.habitchallenge_android.databinding.ItemRankBinding
import com.gdgofftival4.habitchallenge_android.extension.setCircleImageUri
import com.gdgofftival4.habitchallenge_android.room.model.RankUiResponse


class RankHolderPage internal constructor(
    itemView: View,
    val onClick: (position: Int) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemRankBinding.bind(itemView)

    var data: RankUiResponse? = null
    fun onBind(data: RankUiResponse, isTop: Boolean = false) {
        this.data = data
        binding.rankMain.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onClick.invoke(position)
            }
        }
        binding.userImage.setCircleImageUri(Uri.parse(data.userImg), R.drawable.ic_profile_default)
        binding.userRankBadge.isVisible = isTop

        binding.userName.text  = data.userName
        binding.userRank.text = "+"+data.userCombo.toString()
    }
}
