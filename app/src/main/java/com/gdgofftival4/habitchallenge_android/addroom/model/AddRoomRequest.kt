package com.gdgofftival4.habitchallenge_android.addroom.model

import android.media.tv.TvContract.Programs.Genres.SPORTS
import androidx.annotation.DrawableRes
import com.gdgofftival4.habitchallenge_android.R
import com.google.gson.annotations.SerializedName

data class AddRoomRequest(
    val category: Category? = null,
    val title: String? = null,
    val description: String? = null
)