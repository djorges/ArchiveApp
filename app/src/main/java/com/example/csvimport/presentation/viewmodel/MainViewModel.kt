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

/**
 *
 *
 *
 *
 * */
/*
data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val loginSuccess: Boolean = false
)


data class SignupUiState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val signupSuccess: Boolean = false
)

@KoinViewModel
class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.update {
            it.copy(email = email)
        }
    }

    fun onPasswordChange(password: String) {
        _uiState.update {
            it.copy(password = password)
        }
    }

    fun login() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true, error = null)
            }

            // login...
        }
    }
}

*/
@KoinViewModel
class MainViewModel(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
): ViewModel() {
    private val _signupState = MutableStateFlow<UiState<FirebaseUser>>(UiState.Initial)
    val signupState: StateFlow<UiState<FirebaseUser>> = _signupState.asStateFlow()

    private val _loginState = MutableStateFlow<UiState<FirebaseUser>>(UiState.Initial)

    val loginState: StateFlow<UiState<FirebaseUser>> = _loginState.asStateFlow()

    private val _forgotPasswordState = MutableStateFlow<UiState<String>>(UiState.Initial)
    val forgotPasswordState: StateFlow<UiState<String>> = _forgotPasswordState.asStateFlow()

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

    fun loginUser() {
        viewModelScope.launch {
            _loginState.value = UiState.Loading

            val currentEmail = _email.value
            val currentPassword = _password.value

            if (
                currentEmail.isNotEmpty() &&
                currentPassword.isNotEmpty()
            ) {
                val result = authRepository.login(
                    currentEmail,
                    currentPassword
                )

                _loginState.value = result
            }
        }
    }

    fun forgotPassword() {
        viewModelScope.launch {
            _forgotPasswordState.value = UiState.Loading

            val currentEmail = _email.value

            if (currentEmail.isNotEmpty()) {
                val result = authRepository.forgotPassword(currentEmail)

                _forgotPasswordState.value = result
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }


}