package com.gdgofftival4.habitchallenge_android.detail.model

import android.net.Uri
import com.gdgofftival4.habitchallenge_android.detail.DetailUiModel
import com.gdgofftival4.habitchallenge_android.detail.UserContentState
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

fun DetailResponse.toUiModel(): DetailUiModel {
    return DetailUiModel(
        point = user.point,
        nickname = user.nickname,
        userImageUri = Uri.parse(user.image_url),
        postImageUri = Uri.parse(post_image_url),
        status = UserContentState.parse(status),
        goodCount = up,
        badCount = down,
        createdDate = created_date
    )
}