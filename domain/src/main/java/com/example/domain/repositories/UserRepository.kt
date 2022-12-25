package com.example.domain.repositories

import com.example.domain.dto.CustomFirebaseUser
import com.example.domain.entities.User

interface UserRepository {
    suspend fun signInWithGoogle(idToken: String) : CustomFirebaseUser
    suspend fun signInWithEmailAndPassword(email: String, password: String) : Boolean
    suspend fun signUpWithEmailAndPassword(email: String, name: String, password: String) : Boolean
    suspend fun saveUserInDatabase(user: User) : Unit
    suspend fun getOTPRequest(mobileNumber: String): Boolean
}