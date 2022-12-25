package com.example.sampleapp.application.presentation.util

sealed interface Validator<in T> {

    fun validate(param: T): Boolean
    var errorMessage: String?

    object MobileValidator : Validator<String> {
        override var errorMessage: String? = ""
        override fun validate(param: String): Boolean {
            return if (Regex(MOBIL_REGEX).containsMatchIn(param)) {
                errorMessage = null
                true
            } else {
                errorMessage = "Invalid Number"
                false
            }
        }
    }

    object OtpValidator : Validator<String?> {
        override var errorMessage: String? = "Invalid OTP!"
        override fun validate(param: String?): Boolean {

            return if (!param.isNullOrBlank() && param.length == OTP_LENGTH) {
                errorMessage = null
                true
            } else {
                false
            }
        }
    }

    object EmailIdValidator : Validator<String?> {
        override var errorMessage: String? = "Invalid Email/User Id"
        override fun validate(param: String?): Boolean {
            return when {
                param.isNullOrBlank() -> {
                    errorMessage = "email/user id can not be empty"
                    false
                }
                param.length !in 6..49 -> {
                    errorMessage = "invalid Email!"
                    false
                }
                else -> {
                    errorMessage = null
                    true
                }
            }
        }
    }

    object PasswordValidator : Validator<String?> {
        override var errorMessage: String? = "Invalid Password"
        override fun validate(param: String?): Boolean {
            return when {
                param.isNullOrBlank() -> {
                    errorMessage = "Password can not be empty"
                    false
                }
                param.length !in 6..49 -> {
                    errorMessage = "Invalid password!"
                    false
                }
                else -> {
                    errorMessage = null
                    true
                }
            }
        }
    }

    companion object {
        private const val MOBIL_REGEX = "^([+]\\d{2})?\\d{10}\$"
        const val OTP_LENGTH = 6
    }
}
