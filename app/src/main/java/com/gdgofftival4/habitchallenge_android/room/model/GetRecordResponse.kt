package com.gdgofftival4.habitchallenge_android.room.model

import com.google.gson.annotations.SerializedName

data class GetRecordResponse(
    @SerializedName("data") val data: List<RecordResponse>
    )