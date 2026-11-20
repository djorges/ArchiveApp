package com.example.csvimport.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.tooling.preview.Preview
import com.example.csvimport.presentation.ui.theme.CsvImportTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.csvimport.R
import com.example.csvimport.domain.UiState
import com.google.firebase.auth.FirebaseUser

/**
 * TODO:
 * - add stich design.
 * - set signinb button
 * */
/*
@Composable
fun SignupContent(
    modifier: Modifier = Modifier,
    name: String,
    email: String,
    password: String,
    signupState: UiState<FirebaseUser>,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignup: () -> Unit = {},
    onNavigateSignin: () -> Unit = {},
    onGoogleSignUp: () -> Unit = {},
    onFacebookSignUp: () -> Unit = {}
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var confirmPasswordVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Join team",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation =
                if (passwordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
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
                        contentDescription = null
                    )
                }
            }
        )

        Spacer(Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onSignup,
            enabled = signupState !is UiState.Loading,
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Create account")
        }

        Spacer(Modifier.height(16.dp))

        OutlinedButton (
            modifier = Modifier.fillMaxWidth(),
            onClick = onNavigateSignin,
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Sign In")
        }

        Spacer(Modifier.height(24.dp))

        //show message
        when (signupState) {
            is UiState.Loading -> CircularProgressIndicator()

            is UiState.Success ->
                Text(
                    text = "Signed up successfully. Go to Login screen",
                    color = MaterialTheme.colorScheme.primary
                )

            is UiState.Failure ->
                //Show error message
                Text(
                    text = "Error signing up",
                    color = MaterialTheme.colorScheme.error
                )
            else -> Unit
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignupContentPreview() {
    CsvImportTheme {
        SignupContent(
            name = "Diego Gómez",
            email = "diego@example.com",
            password = "123456",
            signupState = UiState.Initial,
            onNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onSignup = {}
        )
    }
}*/


@Composable
fun SignupContent(
    modifier: Modifier = Modifier,
    name: String,
    email: String,
    password: String,
    signupState: UiState<FirebaseUser>,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignup: () -> Unit = {},
    onNavigateSignin: () -> Unit = {},
    onGoogleSignUp: () -> Unit = {},
    onFacebookSignUp: () -> Unit = {}
) {
    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    var confirmPassword by rememberSaveable {
        mutableStateOf("")
    }

    var confirmPasswordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    val isLoading = signupState is UiState.Loading

    val passwordsMatch =
        password.isNotEmpty() &&
                confirmPassword.isNotEmpty() &&
                password == confirmPassword

    val isFormValid =
        name.isNotBlank() &&
                email.isNotBlank() &&
                android.util.Patterns.EMAIL_ADDRESS
                    .matcher(email.trim()).matches() &&
                password.isNotBlank() &&
                passwordsMatch

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surface,
                    shadowElevation = 3.dp
                ) {
                    Column(
                        modifier = Modifier.padding(
                            horizontal = 20.dp,
                            vertical = 24.dp
                        ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        // Logo
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(
                                    R.drawable.csv_master_icon
                                ),
                                contentDescription = "CSV Master",
                                modifier = Modifier.size(28.dp)
                            )

                            Spacer(Modifier.width(8.dp))

                            Text(
                                text = "CSV Master",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Spacer(Modifier.height(24.dp))

                        Text(
                            text = "Crea tu cuenta",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(6.dp))

                        Text(
                            text = "Únete a nosotros y domina tus datos CSV hoy mismo.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )

                        Spacer(Modifier.height(24.dp))

                        // Nombre completo
                        SignupTextField(
                            value = name,
                            onValueChange = onNameChange,
                            label = "NOMBRE COMPLETO",
                            placeholder = "Ingresa tu nombre completo",
                            enabled = !isLoading,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )

                        Spacer(Modifier.height(12.dp))

                        // Email
                        SignupTextField(
                            value = email,
                            onValueChange = onEmailChange,
                            label = "CORREO ELECTRÓNICO",
                            placeholder = "nombre@ejemplo.com",
                            enabled = !isLoading,
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        )

                        Spacer(Modifier.height(12.dp))

                        // Password
                        SignupTextField(
                            value = password,
                            onValueChange = onPasswordChange,
                            label = "CONTRASEÑA",
                            placeholder = "Crea una contraseña segura",
                            enabled = !isLoading,
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next,
                            visualTransformation = if (passwordVisible) {
                                VisualTransformation.None
                            } else {
                                PasswordVisualTransformation()
                            },
                            trailingContent = {
                                IconButton(
                                    onClick = {
                                        passwordVisible = !passwordVisible
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(
                                            if (passwordVisible) {
                                                R.drawable.baseline_visibility_24
                                            } else {
                                                R.drawable.visibility_off_24px
                                            }
                                        ),
                                        contentDescription = "Mostrar u ocultar contraseña"
                                    )
                                }
                            }
                        )

                        Spacer(Modifier.height(12.dp))

                        // Confirmar password
                        SignupTextField(
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
                            label = "CONFIRMAR CONTRASEÑA",
                            placeholder = "Repite tu contraseña",
                            enabled = !isLoading,
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done,
                            visualTransformation = if (confirmPasswordVisible) {
                                VisualTransformation.None
                            } else {
                                PasswordVisualTransformation()
                            },
                            trailingContent = {
                                IconButton(
                                    onClick = {
                                        confirmPasswordVisible =
                                            !confirmPasswordVisible
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(
                                            if (confirmPasswordVisible) {
                                                R.drawable.baseline_visibility_24
                                            } else {
                                                R.drawable.visibility_off_24px
                                            }
                                        ),
                                        contentDescription = "Mostrar u ocultar confirmación"
                                    )
                                }
                            }
                        )

                        // Validación de contraseñas
                        if (
                            confirmPassword.isNotEmpty() &&
                            !passwordsMatch
                        ) {
                            Text(
                                text = "Las contraseñas no coinciden",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp),
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        Spacer(Modifier.height(14.dp))

                        // Botón de registro
                        Button(
                            onClick = onSignup,
                            enabled = isFormValid && !isLoading,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(44.dp),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            if (isLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(20.dp),
                                    strokeWidth = 2.dp,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            } else {
                                Text(
                                    text = "Registrarse",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        // Estados del registro
                        when (signupState) {
                            is UiState.Failure -> {
                                Spacer(Modifier.height(8.dp))

                                Text(
                                    text = "Error al crear la cuenta",
                                    color = MaterialTheme.colorScheme.error,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }

                            else -> Unit
                        }

                        Spacer(Modifier.height(24.dp))

                        // Separador OR
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            HorizontalDivider(
                                modifier = Modifier.weight(1f),
                                color = MaterialTheme.colorScheme.outlineVariant
                            )

                            Text(
                                text = "O",
                                modifier = Modifier.padding(horizontal = 14.dp),
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            HorizontalDivider(
                                modifier = Modifier.weight(1f),
                                color = MaterialTheme.colorScheme.outlineVariant
                            )
                        }

                        Spacer(Modifier.height(18.dp))

                        // Google
                        SocialSignupButton(
                            text = "Registrarse con Google",
                            iconRes = R.drawable.google_logo,
                            onClick = onGoogleSignUp,
                            enabled = !isLoading
                        )

                        // Facebook
                        SocialSignupButton(
                            text = "Registrarse con Facebook",
                            iconRes = R.drawable.facebook_logo,
                            onClick = onFacebookSignUp,
                            enabled = !isLoading
                        )

                        Spacer(Modifier.height(20.dp))

                        // Ir a Login
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "¿Ya tienes una cuenta? ",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            Text(
                                text = "Iniciar sesión",
                                modifier = Modifier.clickable(
                                    enabled = !isLoading,
                                    onClick = onNavigateSignin
                                ),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SignupTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    enabled: Boolean,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingContent: (@Composable () -> Unit)? = null
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            modifier = Modifier.padding(bottom = 6.dp),
            style = MaterialTheme.typography.labelSmall,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodySmall
                )
            },
            singleLine = true,
            enabled = enabled,
            visualTransformation = visualTransformation,
            trailingIcon = trailingContent,
            shape = RoundedCornerShape(4.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            )
        )
    }
}

@Composable
private fun SocialSignupButton(
    text: String,
    iconRes: Int,
    onClick: () -> Unit,
    enabled: Boolean
) {
    TextButton(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 44.dp)
    ) {
        Image(
            painter = painterResource(iconRes),
            contentDescription = null,
            modifier = Modifier.size(18.dp)
        )

        Spacer(Modifier.width(10.dp))

        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = false,
    device = "spec:width=360dp,height=800dp"
)
@Composable
private fun SignupPreview() {
    CsvImportTheme {
        SignupContent(
            name = "",
            email = "",
            password = "",
            signupState = UiState.Initial,
            onNameChange = {},
            onEmailChange = {},
            onPasswordChange = {}
        )
    }
}