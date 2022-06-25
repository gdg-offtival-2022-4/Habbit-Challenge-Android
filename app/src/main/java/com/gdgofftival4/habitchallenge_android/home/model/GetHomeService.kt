package com.gdgofftival4.habitchallenge_android.home.model

import com.gdgofftival4.habitchallenge_android.common.HabitChallengeConfig.userId
import com.gdgofftival4.habitchallenge_android.room.model.RankUiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface GetHomeService {
    @GET("/main")
    suspend fun getHommeList(@Query("userId") userId: String): Response<List<RoomUiResponse>>
}