package com.gdgofftival4.habitchallenge_android.detail.model

import com.google.gson.annotations.SerializedName

data class DetailResponse(
    @SerializedName("user") val user: UserResponse,
    @SerializedName("post_image_url") val post_image_url: String,
    @SerializedName("created_date") val created_date: String,
    @SerializedName("status") val status: String,
    @SerializedName("up") val up: Int,
    @SerializedName("down") val down: Int
    )

//{
//    "post_image_url": "http://",
//    "created_date": "2022년 06월 22일",
//    "status": "PENDING",
//    "up": 1, // 지금 고정값임
//    "down": 2 // 지금 고정값임 ㅋㅋ
//}