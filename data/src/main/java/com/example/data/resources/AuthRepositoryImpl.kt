package com.example.data.resources

import com.example.data.api.AuthApi
import com.example.domain.entities.response.Response
import com.example.domain.repositories.AuthRepository
import com.example.domain.usecases.OTPAuthResponse
import com.example.domain.usecases.util.LoginResponse
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
) : AuthRepository {

    override suspend fun signInWithOTP(username: String, passKey: String): Response<OTPAuthResponse> {
        return authApi.loginWithOTP(username, passKey)
    }

    override suspend fun signIn(userId: String, password: String): Response<LoginResponse> {
        return authApi.signIn(userId,password)
    }

}