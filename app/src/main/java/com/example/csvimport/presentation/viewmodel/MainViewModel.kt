package com.example.csvimport.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csvimport.data.repository.AuthRepository
import com.example.csvimport.data.repository.UserRepository
import com.example.csvimport.domain.AuthState
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
    private val _signupState = MutableStateFlow<AuthState<FirebaseUser>>(AuthState.Initial)
    val signupState: StateFlow<AuthState<FirebaseUser>> = _signupState.asStateFlow()

    var name by mutableStateOf("")
        private set
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    fun onEmailChange(newValue: String) { email = newValue }
    fun onPasswordChange(newValue: String) { password = newValue }

    fun onNameChange(newValue: String) { name = newValue }

    fun signupUser() {
        viewModelScope.launch {
            _signupState.value = AuthState.Loading

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {

                val result = authRepository.signup(name,email, password)

                if (result is AuthState.Success) {
                    userRepository.createUser(result.data.uid, name)
                }
                _signupState.value = result
            }
        }
    }
}