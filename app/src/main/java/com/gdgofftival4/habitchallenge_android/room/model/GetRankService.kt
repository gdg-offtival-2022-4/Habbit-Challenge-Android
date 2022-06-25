package com.gdgofftival4.habitchallenge_android.room.model

import retrofit2.Response
import retrofit2.http.GET

interface GetRankService {
    @GET("/room/rank")
    suspend fun getRankList(): Response<RankResponse>
}