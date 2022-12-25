package com.example.domain.repositories

import com.example.domain.entities.response.Response
import com.example.domain.usecases.OTPAuthResponse
import com.example.domain.usecases.util.LoginResponse

interface AuthRepository {
    suspend fun signInWithOTP(username: String, passKey: String): Response<OTPAuthResponse>
    suspend fun signIn(userId: String, password: String): Response<LoginResponse>
}