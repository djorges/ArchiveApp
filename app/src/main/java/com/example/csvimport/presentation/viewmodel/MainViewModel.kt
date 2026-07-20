package com.example.csvimport.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csvimport.data.repository.AuthRepository
import com.example.csvimport.data.repository.UserRepository
import com.example.csvimport.domain.UiState
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class MainViewModel(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
): ViewModel() {
    private val _signupState = MutableStateFlow<UiState<FirebaseUser>>(UiState.Initial)
    val signupState: StateFlow<UiState<FirebaseUser>> = _signupState.asStateFlow()

    // Estados usando StateFlow
    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    fun onEmailChange(newValue: String) { _email.value = newValue }
    fun onPasswordChange(newValue: String) { _password.value = newValue }
    fun onNameChange(newValue: String) { _name.value = newValue }

    fun signupUser() {
        viewModelScope.launch {
            _signupState.value = UiState.Loading

            val currentName = _name.value
            val currentEmail = _email.value
            val currentPassword = _password.value

            if (currentName.isNotEmpty() && currentEmail.isNotEmpty() && currentPassword.isNotEmpty()) {
                val result = authRepository.signup(currentName, currentEmail, currentPassword)
                if (result is UiState.Success) {
                    userRepository.createUser(result.data.uid, currentName)
                }
                _signupState.value = result
            }
        }
    }
}