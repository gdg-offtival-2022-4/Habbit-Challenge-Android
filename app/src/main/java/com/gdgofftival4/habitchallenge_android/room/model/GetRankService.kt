package com.gdgofftival4.habitchallenge_android.room.model

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface GetRankService {
    @GET("room_rank")
    suspend fun getRankList(@Query("room_id") room_id: String): Response<List<RankUiResponse>>
}