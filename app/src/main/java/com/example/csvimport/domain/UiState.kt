package com.example.csvimport.domain

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    object Initial : UiState<Nothing>()

    data class Success<out T>(val data: T) : UiState<T>()
    data class Failure(val exception: Exception) : UiState<Nothing>()
}