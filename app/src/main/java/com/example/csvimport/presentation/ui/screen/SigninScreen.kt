package com.example.csvimport.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.csvimport.R
import com.example.csvimport.domain.UiState
import com.example.csvimport.presentation.ui.theme.CsvImportTheme
import com.example.csvimport.presentation.ui.theme.Surface
import com.example.csvimport.presentation.viewmodel.MainViewModel
import com.google.firebase.auth.FirebaseUser
import org.koin.compose.viewmodel.koinViewModel

/**
 * TODO:
 * - siggnin -> success -> navigate to address screen
 * -         -> failure -> show snackbar with error message +++++++++++
 *           -> Loading -> show progress bar   ++++++++
 * - onSignUp -> navigate to signup screen
 * - onForgotPassword -> navigate to forgot password screen
 * - onGoogleSignIn -> sign in with google
 * - onFacebookSignIn -> sign in with facebook
 * */
@Composable
fun SigninScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit,
    onSignUp: () -> Unit,
    onForgotPassword: () -> Unit
) {
    val email by viewModel.email.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val loginState by viewModel.loginState.collectAsStateWithLifecycle()

    // Navigate to address screen if login is successful
    LaunchedEffect(loginState) {
        if (loginState is UiState.Success) {
            onLoginSuccess()
        }
    }

    // Show message
    when (loginState) {
        is UiState.Loading -> CircularProgressIndicator()
        is UiState.Failure ->
            //Show error message
            Text(
                text = "Error signing up",
                color = MaterialTheme.colorScheme.error
            )
        else -> Unit
    }

    SigninContent(
        email = email,
        password = password,
        isLoading = loginState is UiState.Loading,
        errorMessage = if (loginState is UiState.Failure) {
            (loginState as UiState.Failure).exception.message
        } else {
            null
        },
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onSignIn = viewModel::loginUser,
        onForgotPassword = onForgotPassword,
        onGoogleSignIn = { },
        onFacebookSignIn = { },
        onLoginSuccess = onLoginSuccess,
        onSignUp = onSignUp
    )
}

@Composable
fun SigninContent(
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    isLoading: Boolean,
    errorMessage: String?,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onForgotPassword: () -> Unit,
    onGoogleSignIn: () -> Unit,
    onFacebookSignIn: () -> Unit,
    onLoginSuccess: () -> Unit,
    onSignIn: () -> Unit,
    onSignUp: () -> Unit
) {

    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(12.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 3.dp
            ) {
                Column(
                    modifier = Modifier.padding(
                        horizontal = 20.dp,
                        vertical = 28.dp
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    /* Logo, Title, Description */
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(4.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(
                                R.drawable.csv_master_icon
                            ),
                            contentDescription = "CSV Master",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "CSV Master",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Sign in to manage your data mapping.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(22.dp))

                    /* Form inputs */
                    // Email
                    OutlinedTextField(
                        value = email,
                        onValueChange = onEmailChange,
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Email")
                        },
                        placeholder = {
                            Text("Enter your email")
                        },
                        singleLine = true,
                        enabled = !isLoading,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Password
                    OutlinedTextField(
                        value = password,
                        onValueChange = onPasswordChange,
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Password")
                        },
                        placeholder = {
                            Text("Enter your password")
                        },
                        singleLine = true,
                        enabled = !isLoading,
                        visualTransformation =
                            if (passwordVisible) {
                                VisualTransformation.None
                            } else {
                                PasswordVisualTransformation()
                            },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    passwordVisible = !passwordVisible
                                }
                            ) {
                                Icon(
                                    painter = painterResource(
                                        if (passwordVisible)
                                            R.drawable.baseline_visibility_24
                                        else
                                            R.drawable.visibility_off_24px
                                    ),
                                    contentDescription =
                                        if (passwordVisible) {
                                            "Hide password"
                                        } else {
                                            "Show password"
                                        }
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        )
                    )

                    // Forgot password
                    Text(
                        text = "Forgot Password?",
                        modifier = Modifier
                            .align(Alignment.End)
                            .clickable(
                                enabled = !isLoading,
                                onClick = onForgotPassword
                            )
                            .padding(top = 8.dp),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    // Error
                    if (errorMessage != null) {
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = errorMessage,
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Sign in
                    Button(
                        onClick = onSignIn,
                        enabled = email.isNotBlank() &&
                                password.isNotBlank() &&
                                !isLoading,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text("Sign In  →")
                        }
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    // OR
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HorizontalDivider(
                            modifier = Modifier.weight(1f)
                        )

                        Text(
                            text = "OR",
                            modifier = Modifier.padding(
                                horizontal = 12.dp
                            ),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary
                        )

                        HorizontalDivider(
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Google
                    OutlinedButton(
                        onClick = onGoogleSignIn,
                        enabled = !isLoading,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(36.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(
                                R.drawable.google_logo
                            ),
                            contentDescription = "Google",
                            modifier = Modifier.size(18.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text("Continue with Google")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Facebook
                    OutlinedButton(
                        onClick = onFacebookSignIn,
                        enabled = !isLoading,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(36.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(
                                R.drawable.facebook_logo
                            ),
                            contentDescription = "Facebook",
                            modifier = Modifier.size(18.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text("Continue with Facebook")
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    // Sign up
                    Row {
                        Text(
                            text = "Don't have an account? ",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Text(
                            text = "Sign up",
                            modifier = Modifier.clickable(
                                enabled = !isLoading,
                                onClick = onSignUp
                            ),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun SigninPreview() {
    CsvImportTheme {
        SigninContent(
            email = "",
            password = "",
            isLoading = false,
            errorMessage = null,
            onEmailChange = {},
            onPasswordChange = {},
            onSignIn = {},
            onForgotPassword = {},
            onGoogleSignIn = {},
            onFacebookSignIn = {},
            onSignUp = {},
            onLoginSuccess = {}
        )
    }
}