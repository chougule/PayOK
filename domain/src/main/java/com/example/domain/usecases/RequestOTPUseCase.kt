package com.example.domain.usecases

import com.example.domain.di.IoDispatcher
import com.example.domain.repositories.UserRepository
import com.example.domain.usecases.core.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RequestOTPUseCase @Inject constructor(
    val userRepository: UserRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CoroutineUseCase<String, Boolean>(dispatcher) {
    override suspend fun execute(params: String): Boolean {
        return userRepository.getOTPRequest(params)
    }
}