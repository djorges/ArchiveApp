package com.example.csvimport.domain

sealed class AuthState<out T> {
    object Loading : AuthState<Nothing>()
    object Initial : AuthState<Nothing>()

    data class Success<out T>(val data: T) : AuthState<T>()
    data class Failure(val exception: Exception) : AuthState<Nothing>()
}