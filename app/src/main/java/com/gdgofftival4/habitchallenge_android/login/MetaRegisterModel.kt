package com.gdgofftival4.habitchallenge_android.login

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MetaRegisterModel(
    val email: String,
    val password: String,
): Parcelable