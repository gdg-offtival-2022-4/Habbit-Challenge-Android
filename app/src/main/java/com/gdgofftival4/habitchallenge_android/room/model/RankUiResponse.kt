package com.gdgofftival4.habitchallenge_android.room.model

import com.google.gson.annotations.SerializedName

data class RankUiResponse(
    @SerializedName("rank") val rank: Int,
    @SerializedName("user_image_url") val userImg: String,
    @SerializedName("nickname") val userName: String,
    @SerializedName("point") val userCombo: Int
    )
