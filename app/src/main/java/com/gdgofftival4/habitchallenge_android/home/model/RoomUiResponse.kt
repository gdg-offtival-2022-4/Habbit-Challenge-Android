package com.gdgofftival4.habitchallenge_android.home.model

import androidx.annotation.DrawableRes
import com.gdgofftival4.habitchallenge_android.R
import com.google.gson.annotations.SerializedName

data class RoomUiResponse(
    @SerializedName("room_id") val room_id: String,
    @SerializedName("title") val title: String,
    @SerializedName("category") val category: String,
    @SerializedName("content") val content: String,
    @SerializedName("user_image_urls") val user_image_urls: List<String>
    )


