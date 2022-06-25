package com.gdgofftival4.habitchallenge_android.addroom.model

import android.media.tv.TvContract.Programs.Genres.SPORTS
import androidx.annotation.DrawableRes
import com.gdgofftival4.habitchallenge_android.R
import com.google.gson.annotations.SerializedName

data class CategoryRequest(
    @SerializedName("categoryIdx") val categoryIdx: Int,
    @SerializedName("categoryName") val categoryName: String,
    @SerializedName("userName") val userName: String,
    @SerializedName("userCombo") val userCombo: Int,
)

enum class Category(val idx: String, val categoryName: String, @DrawableRes val imageRes: Int) {
    SLEEP("1", "수면", R.drawable.ic_sleep),
    DIET("2","식단", R.drawable.ic_diet),
    STUDY("3","공부", R.drawable.ic_study),
    FITNESS("4","운동", R.drawable.ic_finess),
    OTHER("5","기타", R.drawable.ic_more)
}

data class CategoryUiModel(
    val category: Category,
    var isSelected: Boolean
)