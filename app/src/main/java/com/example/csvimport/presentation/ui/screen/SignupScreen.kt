package com.example.csvimport.presentation.ui.screen


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.csvimport.presentation.viewmodel.MainViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = koinViewModel(),
    onSignin: () -> Unit
) {
    val signupState by viewModel.signupState.collectAsStateWithLifecycle()
    val name by viewModel.name.collectAsStateWithLifecycle()
    val email by viewModel.email.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()

    SignupContent(
        modifier = modifier,
        name = name,
        email = email,
        password = password,
        signupState = signupState,
        onNameChange = viewModel::onNameChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onSignup = viewModel::signupUser,
        onSignin = onSignin
    )
}