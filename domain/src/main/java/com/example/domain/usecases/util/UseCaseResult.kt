package com.example.domain.usecases.util

import androidx.lifecycle.MutableLiveData

sealed class UseCaseResult<out R> {

    data class Success<out T>(val response: T) : UseCaseResult<T>()
    data class Error(val exception: Exception) : UseCaseResult<Nothing>()
    object Loading : UseCaseResult<Nothing>()
    object NetworkError: UseCaseResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[response=$response]"
            is Error -> "Error[exception=$exception]"
            is NetworkError -> "NetworkError"
            Loading -> "Loading"
            else -> "ERrord"
        }
    }
}

/**
 * `true` if [UseCaseResult] is of type [Success] & hoxlds non-null [Success.data].
 */
val UseCaseResult<*>.succeeded
    get() = this is UseCaseResult.Success && response != null

fun <T> UseCaseResult<T>.successOr(fallback: T): T {
    return (this as? UseCaseResult.Success<T>)?.response ?: fallback
}

val <T> UseCaseResult<T>.response: T?
    get() = (this as? UseCaseResult.Success)?.response

/**
 * Updates value of [liveData] if [UseCaseResult] is of type [Success]
 */
inline fun <reified T> UseCaseResult<T>.updateOnSuccess(liveData: MutableLiveData<T>) {
    if (this is UseCaseResult.Success) {
        liveData.value = response
    }
}