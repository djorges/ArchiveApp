package com.example.csvimport.presentation.ui.screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.csvimport.presentation.viewmodel.AddressViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun AddressScreen(
    modifier: Modifier = Modifier,
    viewModel: AddressViewModel = koinViewModel(),
    onAddressClick: (Int) -> Unit
){
    val addresses by viewModel.addresses.collectAsStateWithLifecycle()
    val selectedAddress by viewModel.selectedAddress.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState()}
    val searchText by viewModel.searchText.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.uiMessage.collect { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    AddressContent(
        snackbarHostState = snackbarHostState,
        addresses = addresses,
        selectedAddress = selectedAddress,
        onDismissEditDialog = {
            viewModel.onDismissEditDialog()
        },
        onEditAddress = {
            viewModel.onEditAddress(it)
        },
        onSaveAddress = { newCity, newState ->
            viewModel.saveCityState(this.addressId, newCity.trim(), newState.trim())
        },
        searchText = searchText,
        onSearchChange = viewModel::onSearchTextChange,
    )
}

