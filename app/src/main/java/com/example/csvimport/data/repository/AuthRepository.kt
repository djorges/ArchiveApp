package com.example.csvimport.data.repository

import com.example.csvimport.domain.UiState
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    fun currentUserId(): String?
    suspend fun login(email: String, password: String): UiState<FirebaseUser>
    suspend fun signup(name: String, email: String, password: String): UiState<FirebaseUser>
    fun logout()
}