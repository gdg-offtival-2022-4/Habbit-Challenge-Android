package com.gdgofftival4.habitchallenge_android.home.model

import com.google.gson.annotations.SerializedName

data class Room(
    @SerializedName("roomImg") val roomImg: String,
    @SerializedName("roomIdx") val roomIdx: Int,
    @SerializedName("roomTitle") val roomTitle: String,
    @SerializedName("userOne") val userOne: String,
    @SerializedName("userTwo") val userTwo: String,
    @SerializedName("userThree") val userThree: String
    )