package com.example.domain.usecases

import com.example.domain.di.IoDispatcher
import com.example.domain.dto.CustomFirebaseUser
import com.example.domain.repositories.UserRepository
import com.example.domain.usecases.core.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SignInWithGoogleUseCase @Inject constructor(
    private val repository: UserRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CoroutineUseCase<String, CustomFirebaseUser>(dispatcher) {
    override suspend fun execute(params: String): CustomFirebaseUser {
        return repository.signInWithGoogle(idToken = params)
    }
}