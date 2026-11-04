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

    override fun currentUserId(): String? =
        firebaseAuth.currentUser?.uid

    override suspend fun login(
        email: String,
        password: String
    ): UiState<FirebaseUser> = executeAuth {
        firebaseAuth
            .signInWithEmailAndPassword(email.trim(), password)
            .await()
            .user
    }

    override suspend fun signup(
        name: String,
        email: String,
        password: String
    ): UiState<FirebaseUser> = executeAuth {
        val user = firebaseAuth
            .createUserWithEmailAndPassword(email.trim(), password)
            .await()
            .user
            ?: throw IllegalStateException("Firebase user is null")

        // Actualizar el nombre del usuario
        user.updateProfile(
            userProfileChangeRequest {
                displayName = name.trim()
            }
        ).await()

        user
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

    private suspend fun executeAuth(
        action: suspend () -> FirebaseUser?
    ): UiState<FirebaseUser> = try {

        val user = action()
            ?: throw IllegalStateException("Firebase user is null")

        UiState.Success(user)

    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        UiState.Failure(e)
    }
}
