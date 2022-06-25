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

enum class Category(name: String, @DrawableRes val imageRes: Int) {
    SPORTS("운동", R.drawable.ic_launcher_background),
    COOKING("요리", R.drawable.ic_launcher_background),
    TRIP("여행", R.drawable.ic_launcher_background),
    A("운동", R.drawable.ic_launcher_background),
    B("요리", R.drawable.ic_launcher_background),
    C("여행", R.drawable.ic_launcher_background),
    D("운동", R.drawable.ic_launcher_background),
    E("요리", R.drawable.ic_launcher_background),
    F("여행", R.drawable.ic_launcher_background)
}

data class CategoryUiModel(
    val category: Category,
    var isSelected: Boolean
)