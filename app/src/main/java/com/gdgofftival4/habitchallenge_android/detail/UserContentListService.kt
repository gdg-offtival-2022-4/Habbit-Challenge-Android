package com.gdgofftival4.habitchallenge_android.detail

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserContentListService {

    @GET("room_rank_detail")
    fun fetchUserContentList(
        @Query("user_id") userId: String,
        @Query("room_id") roomId: String
    ): Call<UserContentListResponse>
}