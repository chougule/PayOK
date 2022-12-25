package com.example.domain.usecases

import com.example.domain.di.IoDispatcher
import com.example.domain.entities.response.Response
import com.example.domain.extensions.toUseCaseResult
import com.example.domain.repositories.AuthRepository
import com.example.domain.usecases.core.CoroutineUseCase
import com.example.domain.usecases.util.LoginResponse
import com.example.domain.usecases.util.UseCaseResult
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : CoroutineUseCase<LoginRequest, UseCaseResult<LoginResponse>>(dispatcher) {
    override suspend fun execute(params: LoginRequest): UseCaseResult<LoginResponse> {
        //return authRepository.signIn(params.userid, params.password).toUseCaseResult()
        //TODO remove mock result when api is ready
        return Response(data = LoginResponse(true), isError = false).toUseCaseResult()
    }
}

data class LoginRequest(val userid: String, val password: String)

