package com.example.csvimport.data

import com.example.csvimport.domain.AuthState
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    fun currentUserId(): String?
    suspend fun login(email: String, password: String): AuthState<FirebaseUser>
    suspend fun signup(name: String, email: String, password: String): AuthState<FirebaseUser>
    fun logout()
}