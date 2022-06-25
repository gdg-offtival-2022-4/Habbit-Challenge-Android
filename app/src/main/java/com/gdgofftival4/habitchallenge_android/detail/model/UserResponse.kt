package com.gdgofftival4.habitchallenge_android.detail.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("point") val point: Int,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("image_url") val image_url: String
    )
//"user": {
//    "point": 23,
//    "nickname": "asd",
//    "image_url": "http://asd"
//},