package com.gdgofftival4.habitchallenge_android.detail.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MetaDetailModel(
    val postId: String,
    val roomId: String,
): Parcelable