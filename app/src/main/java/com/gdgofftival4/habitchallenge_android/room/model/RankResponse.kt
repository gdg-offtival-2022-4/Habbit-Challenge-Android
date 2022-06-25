package com.gdgofftival4.habitchallenge_android.room.model

import com.google.gson.annotations.SerializedName

data class RankResponse(
    @SerializedName("users") val users: List<RankUiResponse>
    )