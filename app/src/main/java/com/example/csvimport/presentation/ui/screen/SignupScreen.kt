package com.example.csvimport.presentation.ui.screen


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.csvimport.domain.UiState
import com.example.csvimport.presentation.viewmodel.MainViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = koinViewModel(),
    onSignupSuccess: () -> Unit = {},
    onNavigateSignin: () -> Unit = {}
) {
    val name by viewModel.name.collectAsStateWithLifecycle()
    val email by viewModel.email.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val signupState by viewModel.signupState.collectAsStateWithLifecycle()

    LaunchedEffect(signupState) {
        if (signupState is UiState.Success) {
            onSignupSuccess()
        }
    }

    SignupContent(
        name = name,
        email = email,
        password = password,
        signupState = signupState,
        onNameChange = viewModel::onNameChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onSignup = viewModel::signupUser,
        onNavigateSignin = onNavigateSignin,
        onGoogleSignUp = {
            // Implementar Google Sign-In
        },
        onFacebookSignUp = {
            // Implementar Facebook Sign-In
        }
    )
}