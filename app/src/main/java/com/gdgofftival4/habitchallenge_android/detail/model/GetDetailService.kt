package com.gdgofftival4.habitchallenge_android.detail.model

import com.gdgofftival4.habitchallenge_android.common.HabitChallengeConfig.userId
import com.gdgofftival4.habitchallenge_android.home.model.RoomUiResponse
import com.gdgofftival4.habitchallenge_android.room.model.RankUiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDetailService {
    @GET("room_post_detail")
    suspend fun getDetailt(
        @Query("room_id") room_id: String,
        @Query("post_id") post_id: String
    ): Response<DetailResponse>
}