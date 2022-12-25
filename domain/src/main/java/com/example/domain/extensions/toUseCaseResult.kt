package com.example.domain.extensions

import com.example.domain.entities.response.DomainException
import com.example.domain.entities.response.Response
import com.example.domain.usecases.util.UseCaseResult

fun <OUT> Response<OUT>.toUseCaseResult(): UseCaseResult<OUT> {
    return if (data != null && !isError) {
        UseCaseResult.Success<OUT>(data)
    } else {
        UseCaseResult.Error(DomainException(message = errorMessage()))
    }
}

fun <ERROR> Response<ERROR>.errorMessage(): String = data.toString()
