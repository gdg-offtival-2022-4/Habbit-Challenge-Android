package com.gdgofftival4.habitchallenge_android.addroom.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
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
            binding.categoryName.text = "click!!"
        }
        else{
            binding.categoryName.text = data.category.name
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

    }
}
