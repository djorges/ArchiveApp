package com.example.csvimport.presentation.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.csvimport.presentation.ui.screen.AddressScreen
import com.example.csvimport.presentation.ui.screen.SignupScreen

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
){
    val backStack = rememberNavBackStack(AppRoutes.Signup)

    NavDisplay(
        modifier = modifier.fillMaxSize(),
        backStack = backStack,
        entryProvider = entryProvider{
            entry<AppRoutes.AddressList> {
                AddressScreen()
            }
            entry<AppRoutes.Signup> {
                SignupScreen()
            }
        }
    )
}