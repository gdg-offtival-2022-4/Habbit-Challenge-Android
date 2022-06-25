package com.gdgofftival4.habitchallenge_android.register

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface RegisterService {

    @Multipart
    @POST("signup")
    suspend fun register(
        @Part("id") email: String,
        @Part("pw") password: String,
        @Part("nickname") nickName: String,
        @Part profileImage: MultipartBody.Part
    ): Call<RegisterResponse>
}