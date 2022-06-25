package com.gdgofftival4.habitchallenge_android.addroom.model

import com.gdgofftival4.habitchallenge_android.common.HabitChallengeConfig.userId
import com.gdgofftival4.habitchallenge_android.home.model.RoomUiResponse
import com.gdgofftival4.habitchallenge_android.room.model.RankUiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PostAddRoomService {
    @POST("room")
    suspend fun getHommeList(@Body request: PostAddRoomRequest): Response<AddRoomResponse>
}