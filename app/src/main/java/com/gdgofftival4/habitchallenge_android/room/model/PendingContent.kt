package com.gdgofftival4.habitchallenge_android.room.model

import com.google.gson.annotations.SerializedName

data class PendingContent(
    @SerializedName("contnetIdx") val contnetIdx: Int,
    @SerializedName("contentImg") val contentImg: String
    )