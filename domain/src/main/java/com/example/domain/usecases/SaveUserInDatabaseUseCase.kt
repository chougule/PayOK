package com.example.domain.usecases

import com.example.domain.di.IoDispatcher
import com.example.domain.entities.User
import com.example.domain.repositories.UserRepository
import com.example.domain.usecases.core.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SaveUserInDatabaseUseCase @Inject constructor(
    private val repository: UserRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CoroutineUseCase<User, Unit>(dispatcher) {
    override suspend fun execute(params: User){
        return repository.saveUserInDatabase(params)
    }
}