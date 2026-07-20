package com.example.csvimport.data.repository

import com.example.csvimport.domain.UiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Singleton

@Singleton(binds = [AuthRepository::class])
class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override fun currentUserId(): String? = firebaseAuth.currentUser?.uid

    override suspend fun login(
        email: String,
        password: String
    ): UiState<FirebaseUser> = try {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        UiState.Success(result.user!!)
    } catch (e: Exception) {
        UiState.Failure(e)
    }


    override suspend fun signup(
        name: String,
        email: String,
        password: String
    ): UiState<FirebaseUser> = try {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()

        UiState.Success(result.user!!)
    } catch (e: Exception) {
        UiState.Failure(e)
    }


    override fun logout() {
        firebaseAuth.signOut()
    }

}