package com.gdgofftival4.habitchallenge_android.room

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface RoomService {
    @Multipart
    @POST("post")
    fun post(
        @Part("user_id") userId: String,
        @Part("room_id") roomId: String,
        @Part image: MultipartBody.Part
    ): Call<PostResponse>

    @GET("room")
    fun fetchRoomMetaData(@Query("roomId") roomId: String): Call<RoomMetaDataResponse>
}
