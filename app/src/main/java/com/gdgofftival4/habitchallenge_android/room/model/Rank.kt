package com.gdgofftival4.habitchallenge_android.room.model

import com.google.gson.annotations.SerializedName

data class Rank(
    @SerializedName("userIdx") val userIdx: Int,
    @SerializedName("userImg") val userImg: String,
    @SerializedName("userName") val userName: String,
    @SerializedName("userCombo") val userCombo: Int
    )