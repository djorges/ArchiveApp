package com.example.csvimport.presentation.nav

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoutes: NavKey{

    @Serializable
    data object Signin: AppRoutes{
    }

    @Serializable
    data object Signup: AppRoutes{
    }

    @Serializable
    data object Dashboard: AppRoutes{
    }

    @Serializable
    data object AddressList: AppRoutes{
    }

    @Serializable
    data class AddressDetail(val addressId: Int): AppRoutes

}