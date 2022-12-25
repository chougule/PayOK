package com.example.data.utils

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import com.example.domain.usecases.util.UseCaseResult
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects

@ExperimentalCoroutinesApi
inline fun <reified T> DocumentReference.collectAsFlow(): Flow<UseCaseResult<T>> {
    return callbackFlow {
        addSnapshotListener { value, error ->
            if (error != null || value == null) {
                if (!isClosedForSend) {
                    trySend(UseCaseResult.Error(Exception(error)))
                }
                close(error)
                return@addSnapshotListener
            }

            if (!isClosedForSend) {
                value.toObject<T>()?.let { data ->
                    trySend(UseCaseResult.Success(data))
                }
            }
        }
        awaitClose()
    }
}

@ExperimentalCoroutinesApi
inline fun <reified T> DocumentReference.collectAsFlowNullable(): Flow<UseCaseResult<T?>> {
    return callbackFlow {
        addSnapshotListener { value, error ->
            if (error != null || value == null) {
                if (!isClosedForSend) {
                    trySend(UseCaseResult.Error(Exception(error)))
                }
                close(error)
                return@addSnapshotListener
            }

            if (!isClosedForSend) {
                trySend(UseCaseResult.Success(value.toObject<T>()))
            }
        }
        awaitClose()
    }
}

@ExperimentalCoroutinesApi
inline fun <reified T : Any> Query.collectAsFlow(
    crossinline action: ((List<T>) -> List<T>
    ) = { data -> data }
): Flow<UseCaseResult<List<T>>> {
    return callbackFlow {
        addSnapshotListener { value, error ->
            if (error != null || value == null) {
                if (!isClosedForSend) {
                    trySend(UseCaseResult.Error(Exception(error)))
                }
                close(error)
                return@addSnapshotListener
            }

            if (!isClosedForSend) {
                trySend(UseCaseResult.Success(action(value.toObjects())))
            }
        }
        awaitClose()
    }
}