package com.example.domain.usecases

import com.example.domain.di.IoDispatcher
import com.example.domain.repositories.UserRepository
import com.example.domain.usecases.SignUpWithEmailAndPasswordUseCase.SignUpParams
import com.example.domain.usecases.core.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher

class SignUpWithEmailAndPasswordUseCase(
    private val repository: UserRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : CoroutineUseCase<SignUpParams, Boolean>(dispatcher) {
    override suspend fun execute(params: SignUpParams): Boolean {
        return repository.signUpWithEmailAndPassword(
            email = params.email,
            name = params.name,
            params.password
        )
    }

    data class SignUpParams(val email: String, val name: String, val password: String)
}