package com.gdgofftival4.habitchallenge_android.room.model

import com.google.gson.annotations.SerializedName

data class RecordResponse(
    @SerializedName("post_id") val post_id: String,
    @SerializedName("post_image_url") val post_image_url: String
    )