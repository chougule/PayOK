package com.example.domain.usecases.core

import com.example.domain.usecases.util.UseCaseResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

abstract class CoroutineUseCase<in IN, OUT>(private val coroutineDispatcher: CoroutineDispatcher) {

    /** Executes the use case asynchronously and returns a [UseCaseResult].
     *
     * @return a [UseCaseResult].
     *
     * @param params the input parameters to run the use case with
     */
    suspend operator fun invoke(params: IN): UseCaseResult<OUT> {
        return try {
            // Moving all use case's executions to the injected dispatcher
            // In production code, this is usually the Default dispatcher (background thread)
            // In tests, this becomes a TestCoroutineDispatcher
            withContext(coroutineDispatcher) {
                execute(params).let {
                    UseCaseResult.Success(it)
                }
            }
        } catch (exception: UnknownHostException) {
            UseCaseResult.NetworkError
        } catch (exception: Exception) {
            UseCaseResult.Error(exception)
        }
    }

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(params: IN): OUT
}