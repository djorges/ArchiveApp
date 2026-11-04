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
) : ViewModel() {

    // Formulario
    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    // Estados de autenticación
    private val _signupState =
        MutableStateFlow<UiState<FirebaseUser>>(UiState.Initial)
    val signupState = _signupState.asStateFlow()

    private val _loginState =
        MutableStateFlow<UiState<FirebaseUser>>(UiState.Initial)
    val loginState = _loginState.asStateFlow()

    // Actualización de campos
    fun onNameChange(value: String) {
        _name.value = value
    }

    fun onEmailChange(value: String) {
        _email.value = value
    }

    fun onPasswordChange(value: String) {
        _password.value = value
    }

    // Registro
    fun signUp() {
        val currentName = _name.value.trim()
        val currentEmail = _email.value.trim()
        val currentPassword = _password.value

        if (!validateSignUp(currentName, currentEmail, currentPassword)) {
            return
        }

        viewModelScope.launch {
            _signupState.value = UiState.Loading

            try {
                val result = authRepository.signup(
                    currentName,
                    currentEmail,
                    currentPassword
                )

                if (result is UiState.Success) {
                    userRepository.createUser(
                        result.data.uid,
                        currentName
                    )
                }

                _signupState.value = result

            } catch (e: Exception) {
                // Adaptar al tipo de error de tu UiState
                _signupState.value = UiState.Error(
                    e.message ?: "Error al registrarse"
                )
            }
        }
    }

    // Login
    fun login() {
        val currentEmail = _email.value.trim()
        val currentPassword = _password.value

        if (!validateLogin(currentEmail, currentPassword)) {
            return
        }

        viewModelScope.launch {
            _loginState.value = UiState.Loading

            try {
                _loginState.value = authRepository.login(
                    currentEmail,
                    currentPassword
                )

            } catch (e: Exception) {
                _loginState.value = UiState.Error(
                    e.message ?: "Error al iniciar sesión"
                )
            }
        }
    }

    // Validaciones
    private fun validateSignUp(
        name: String,
        email: String,
        password: String
    ): Boolean {
        if (name.isBlank() || email.isBlank() || password.isBlank()) {
            _signupState.value = UiState.Error(
                "Todos los campos son obligatorios"
            )
            return false
        }

        return true
    }

    private fun validateLogin(
        email: String,
        password: String
    ): Boolean {
        if (email.isBlank() || password.isBlank()) {
            _loginState.value = UiState.Error(
                "Email y contraseña son obligatorios"
            )
            return false
        }

        return true
    }
}
