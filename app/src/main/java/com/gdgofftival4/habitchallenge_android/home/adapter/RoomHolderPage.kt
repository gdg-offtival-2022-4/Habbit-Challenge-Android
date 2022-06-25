package com.gdgofftival4.habitchallenge_android.home.adapter

import android.view.View
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.gdgofftival4.habitchallenge_android.R
import com.gdgofftival4.habitchallenge_android.databinding.ItemRoomBinding
import com.gdgofftival4.habitchallenge_android.home.model.RoomUiResponse


class RoomHolderPage internal constructor(
    itemView: View,
    val onClick: (roomId: String, roomTitle: String, roomContent: String) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemRoomBinding.bind(itemView)

    var data: RoomUiResponse? = null
    fun onBind(data: RoomUiResponse) {
        this.data = data

        binding.itemRoom.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onClick.invoke(data.room_id, data.title, data.content)
            }
        }

        // room title
        binding.roomTitle.text = data.title

        // roomImg
        CategoryImg.values().forEach { item ->
            if(item.categoryName == data.category){
                binding.roomImage.setImageResource(item.imageRes)
            }
        }

        // userImg
        Glide.with(itemView.context)
            .load(data.user_image_urls[0])
            .transform(CenterCrop(), RoundedCorners(200))
            .into(binding.userOne)

        Glide.with(itemView.context)
            .load(data.user_image_urls[0])
            .transform(CenterCrop(), RoundedCorners(200))
            .into(binding.userTwo)

        Glide.with(itemView.context)
            .load(data.user_image_urls[2])
            .transform(CenterCrop(), RoundedCorners(200))
            .into(binding.userThree)


    }
}

enum class CategoryImg(val categoryName:String, @DrawableRes val imageRes: Int) {
    SLEEP("1",R.mipmap.img_sleep_foreground),
    DIET("2",R.mipmap.img_food_foreground),
    STUDY("3",R.mipmap.img_study_foreground),
    FITNESS("4",R.mipmap.img_fitness_foreground),
    OTHER("5",R.mipmap.img_more_foreground)
    ;

    companion object {
        fun parse(name: String): CategoryImg {
            return values().find { it.name == name } ?: CategoryImg.OTHER
        }
    }
}
