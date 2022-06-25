package com.gdgofftival4.habitchallenge_android.login.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}