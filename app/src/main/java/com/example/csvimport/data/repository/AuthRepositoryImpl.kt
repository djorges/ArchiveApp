package com.example.csvimport.data.repository

import com.example.csvimport.domain.AuthState
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
    ): AuthState<FirebaseUser> = try {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        AuthState.Success(result.user!!)
    } catch (e: Exception) {
        AuthState.Failure(e)
    }


    override suspend fun signup(
        name: String,
        email: String,
        password: String
    ): AuthState<FirebaseUser> = try {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()

        AuthState.Success(result.user!!)
    } catch (e: Exception) {
        AuthState.Failure(e)
    }


    override fun logout() {
        firebaseAuth.signOut()
    }

}