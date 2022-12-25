package com.example.domain.entities.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

//TODO : if its generalised pattern then make it generic
data class Response<T>(
    @SerializedName("error")
    val isError: Boolean,
    val data: T,
) : Serializable

data class ErrorResponse<T>(
    val data: T,
    @SerializedName("msg")
    val message: String
) : Serializable

class DomainException(val code: String? = null, override val message: String) : Exception(message)