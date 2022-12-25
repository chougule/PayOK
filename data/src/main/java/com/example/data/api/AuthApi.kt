package com.example.data.api

import com.example.domain.entities.response.Response
import com.example.domain.usecases.OTPAuthResponse
import com.example.domain.usecases.util.LoginResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {

    @POST("login")
    suspend fun loginWithOTP(
        @Query("username") username: String,
        @Query("pass_key") passKey: String,
    ): Response<OTPAuthResponse>

    @POST("login")
    suspend fun signIn(
        @Query("username") username: String,
        @Query("pass_key") passKey: String,
    ): Response<LoginResponse>
}
