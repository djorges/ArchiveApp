package com.example.csvimport.presentation.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.csvimport.presentation.ui.screen.AddressScreen
import com.example.csvimport.presentation.ui.screen.SigninScreen
import com.example.csvimport.presentation.ui.screen.SignupScreen


/**
 * TODO:
 * - screen for dashboard
 * - screen for forgot password
 * - screen address detail
 * */
@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
){
    val backStack = rememberNavBackStack(
        //AppRoutes.Dashboard
        AppRoutes.Signin
    )

    NavDisplay(
        modifier = modifier.fillMaxSize(),
        backStack = backStack,
        entryProvider = entryProvider{
            entry<AppRoutes.Dashboard> {

            }
            entry<AppRoutes.AddressList> {
                AddressScreen(
                    onAddressClick = { backStack.add(AppRoutes.AddressDetail(it)) }
                )
            }
            entry<AppRoutes.AddressDetail> { key ->
               /* AddressDetailScreen(
                    addressId = key.addressId,
                    onBack = { backStack.removeLastOrNull() }
                )*/
            }
            entry<AppRoutes.Signup> {
                SignupScreen(
                    // on sign up success do not navigate, it shows message
                    onSignin = { backStack.add(AppRoutes.Signin) }
                )
            }
            entry<AppRoutes.Signin> {
                SigninScreen(
                    onLoginSuccess = { backStack.add(AppRoutes.AddressList) },
                    onSignUp = { backStack.add(AppRoutes.Signup) },
                    onForgotPassword = {}
                )
            }
        }
    )
}