package com.gdgofftival4.habitchallenge_android.addroom.model

import android.media.tv.TvContract.Programs.Genres.SPORTS
import androidx.annotation.DrawableRes
import com.gdgofftival4.habitchallenge_android.R
import com.google.gson.annotations.SerializedName

data class AddRoomResponse(
    @SerializedName("room_id") val room_id: String
)
//{
//    "title": "같이 금연습관 만드러용ㅋㅋ",
//    "category": "1", // 1,2,3,4,5,6 이건 상수를 정합시다
//    "content": "ㅋㅋㅋㅋㅋㅋ"
//}