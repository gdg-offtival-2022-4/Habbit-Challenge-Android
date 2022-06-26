package com.gdgofftival4.habitchallenge_android.detail.model

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PostService {
    @GET("room_post_detail")
    fun fetchDetail(
        @Query("room_id") room_id: String,
        @Query("post_id") post_id: String
    ): Call<DetailResponse>

    @POST("room_post_detail_up_down")
    fun doVote(@Body voteRequest: VoteRequest): Call<VoteResponse>
}