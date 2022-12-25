package com.example.domain.usecases

import com.example.domain.di.IoDispatcher
import com.example.domain.repositories.UserRepository
import com.example.domain.usecases.SignInWithEmailAndPasswordUseCase.*
import com.example.domain.usecases.core.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SignInWithEmailAndPasswordUseCase @Inject constructor(
    private val repository: UserRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : CoroutineUseCase<SignInWithEmailParams, Boolean>(dispatcher) {
    override suspend fun execute(params: SignInWithEmailParams): Boolean {
        return repository.signInWithEmailAndPassword(
            email = params.email,
            password = params.password
        )
    }

    data class SignInWithEmailParams(val email: String, val password: String)
}