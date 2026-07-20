package com.example.csvimport.presentation.ui.screen

import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.csvimport.presentation.viewmodel.AddressViewModel
import org.koin.compose.viewmodel.koinViewModel


private val csvMimeTypes = arrayOf(
    "text/csv",
    "text/*",
    "application/vnd.ms-excel"
)

@Composable
fun AddressScreen(
    modifier: Modifier = Modifier,
    viewModel: AddressViewModel = koinViewModel()
){
    val addresses by viewModel.addresses.collectAsStateWithLifecycle()
    val selectedAddress by viewModel.selectedAddress.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState()}


    LaunchedEffect(Unit) {
        viewModel.uiMessage.collect { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    val openCsvLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ){ uri ->
        uri?.let{
            //persist permission
            val contentResolver = context.contentResolver
            try {
                contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }catch (e: SecurityException) {
                Log.e("AddressScreen", "Persist permission failed", e)
            }
            viewModel.importCsv(contentResolver, it)
        }
    }

    AddressContent(
        snackbarHostState = snackbarHostState,
        addresses = addresses,
        selectedAddress = selectedAddress,
        onImportCsv = {
            openCsvLauncher.launch(csvMimeTypes)
        },
        onDismissEditDialog = {
            viewModel.onDismissEditDialog()
        },
        onEditAddress = {
            viewModel.onEditAddress(it)
        },
        onSaveAddress = { newCity, newState ->
            viewModel.saveCityState(this.addressId, newCity.trim(), newState.trim())
        }
    )
}

