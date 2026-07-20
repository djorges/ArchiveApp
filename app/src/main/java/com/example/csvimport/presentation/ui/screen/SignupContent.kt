package com.example.csvimport.presentation.ui.screen

import androidx.compose.ui.tooling.preview.Preview
import com.example.csvimport.presentation.ui.theme.CsvImportTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.csvimport.R
import com.example.csvimport.domain.UiState
import com.google.firebase.auth.FirebaseUser

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
    onSignup: () -> Unit
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

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

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            onClick = onSignup,
            enabled = signupState !is UiState.Loading,
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Create account")
        }

        Spacer(Modifier.height(24.dp))

        //show message
        when (signupState) {
            is UiState.Loading -> CircularProgressIndicator()

            is UiState.Success ->
                Text(
                    text = "Signed up successfully",
                    color = MaterialTheme.colorScheme.primary
                )

            is UiState.Failure ->
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
}