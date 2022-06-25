package com.gdgofftival4.habitchallenge_android.addroom.adapter

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.gdgofftival4.habitchallenge_android.R
import com.gdgofftival4.habitchallenge_android.addroom.model.CategoryUiModel
import com.gdgofftival4.habitchallenge_android.databinding.ItemCategoryBinding


class CategoryHolderPage internal constructor(
    itemView: View,
    val onClick: (position: Int) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemCategoryBinding.bind(itemView)

    var data: CategoryUiModel? = null
    fun onBind(data: CategoryUiModel) {
        this.data = data
        if(data.isSelected){
            binding.categoryImg.setBackgroundResource(R.drawable.shape_round_circle_fill)
            binding.categoryImg.setColorFilter(
                ContextCompat.getColor(itemView.context, R.color.white), PorterDuff.Mode.SRC_IN)
        }
        else{
            binding.categoryImg.setBackgroundResource(R.drawable.shape_round_circle)
            binding.categoryImg.setColorFilter(
                ContextCompat.getColor(itemView.context, R.color.black), PorterDuff.Mode.SRC_IN)
        }
        itemView.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onClick.invoke(position)
            }
        }

        Glide.with(itemView.context)
            .load(data.category.imageRes)
            .transform(CenterCrop())
            .into(binding.categoryImg)

        binding.categoryName.text = data.category.categoryName

    }
}
