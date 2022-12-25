package com.example.data.resources

import com.example.data.utils.FireBasePath
import com.example.domain.dto.CustomFirebaseUser
import com.example.domain.entities.User
import com.example.domain.repositories.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) : UserRepository {

    override suspend fun signInWithGoogle(idToken: String): CustomFirebaseUser {
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        try {
            val user = firebaseAuth.signInWithCredential(credential).await().user!!
            return CustomFirebaseUser(
                uid = user.uid,
                email = user.email.toString(),
                name = user.displayName.toString(),
                profileUrl = user.photoUrl.toString(),
                isValid = true,
                createdAt = Date(System.currentTimeMillis())
            )
        } catch (error: Throwable) {
            throw error
        }
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun signUpWithEmailAndPassword(
        email: String,
        name: String,
        password: String
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun saveUserInDatabase(user: User) {
        val docRef = firestore.document(FireBasePath.getUserDocument(user.uid))
        docRef.set(user).await()
    }

    override suspend fun getOTPRequest(mobileNumber: String): Boolean {
        // TODO write mobile OTP request code here for now lets mock it
        return true
    }
}