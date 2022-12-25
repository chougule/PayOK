package com.example.domain.usecases

import com.example.domain.di.IoDispatcher
import com.example.domain.extensions.toUseCaseResult
import com.example.domain.repositories.AuthRepository
import com.example.domain.usecases.core.CoroutineUseCase
import com.example.domain.usecases.util.UseCaseResult
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class PinAuthRequest(val username: String, val passKey: String)

data class OTPAuthResponse(
    @SerializedName("business_name")
    val businessName: String,
    @SerializedName("mobile_number")
    val mobileNumber: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("user_type")
    val userType: String,
    @SerializedName("token")
    val token: String,
)

class PinAuthUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : CoroutineUseCase<PinAuthRequest, UseCaseResult<OTPAuthResponse>>(dispatcher) {
    override suspend fun execute(params: PinAuthRequest): UseCaseResult<OTPAuthResponse> {
         return authRepository.signInWithOTP(params.username, params.passKey).toUseCaseResult()
    }
}

suspend fun <T> errorHandler(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T) {
    return withContext(dispatcher) {
        apiCall.invoke()
    }
}