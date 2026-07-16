package com.example.csvimport.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Singleton

@Singleton(binds = [UserRepository::class])
class UserRepositoryImpl(
    private val firestore: FirebaseFirestore
) : UserRepository {

    override suspend fun createUser(
        uid: String,
        name: String
    ): Result<Unit> {

        return try {
            val user = hashMapOf(
                "name" to name,
                "role" to "Customer"
            )
            firestore.collection("Users")
                .document(uid)
                .set(user)
                .await()

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}