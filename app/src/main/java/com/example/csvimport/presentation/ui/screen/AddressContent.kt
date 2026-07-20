package com.example.csvimport.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.csvimport.R
import com.example.csvimport.data.db.AddressEntity
import com.example.csvimport.presentation.ui.theme.CsvImportTheme

@Composable
fun AddressContent(
    snackbarHostState: SnackbarHostState,
    addresses: List<AddressEntity>,
    selectedAddress: AddressEntity? = null,
    onImportCsv: () -> Unit,
    onEditAddress: (AddressEntity) -> Unit = {},
    onDismissEditDialog: () -> Unit = {},
    onSaveAddress: AddressEntity.(String, String) -> Unit = { _, _ -> }
) {

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        modifier = Modifier.fillMaxSize()
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = stringResource(
                        R.string.addresses_count,
                        addresses.size
                    ),
                    style = MaterialTheme.typography.titleMedium
                )

                Button(
                    onClick = onImportCsv
                ) {
                    Icon(
                        painter = painterResource(R.drawable.outline_drive_folder_upload_24),
                        contentDescription = null
                    )

                    Spacer(Modifier.width(ButtonDefaults.IconSpacing))

                    Text(stringResource(R.string.import_csv))
                }
            }

            Spacer(Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(addresses, key = { it.addressId }) { item ->
                    AddressRow(address = item, onEdit = onEditAddress)
                }
            }

            //Show dialog if selected
            selectedAddress?.let { address ->
                EditAddressDialog(
                    initialCity = address.city,
                    initialState = address.stateProvince,
                    onDismiss = onDismissEditDialog,
                    onSave = onSaveAddress
                )
            }
        }
    }
}

@Composable
fun EditAddressDialog(
    initialCity: String,
    initialState: String,
    onDismiss: () -> Unit,
    onSave: AddressEntity.(String, String) -> Unit
) {
    TODO("Not yet implemented")
}

@Composable
fun AddressRow(
    modifier: Modifier = Modifier,
    address: AddressEntity,
    onEdit: (AddressEntity) -> Unit
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(modifier = Modifier.weight(1f)) {

                Text(text = "ID: ${address.addressId}", style = MaterialTheme.typography.bodySmall)

                Spacer(modifier = Modifier.height(4.dp))

                Text(text = address.addressLine1, style = MaterialTheme.typography.bodyLarge)

                address.addressLine2?.let {
                    Spacer(modifier = Modifier.height(4.dp))

                    Text(text = it, style = MaterialTheme.typography.bodyMedium)
                }

                Spacer(modifier = Modifier.height(6.dp))

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddressContentPreview() {
    val snackbarHostState = remember { SnackbarHostState() }

    CsvImportTheme {
        AddressContent(
            addresses = listOf(
                AddressEntity(
                    addressId = 1,
                    addressLine1 = "123 Main St",
                    addressLine2 = "Apt 4B",
                    city = "Anytown",
                    stateProvince = "CA",
                    countryRegion = "USA",
                    postalCode = "12345",
                    rowguid = "12345678-1234-1234-1234-123456789012",
                    modifiedDate = "2023-01-01 12:00:00"
                ),
                AddressEntity(
                    addressId = 2,
                    addressLine1 = "456 Elm St",
                    addressLine2 = null,
                    city = "Othertown",
                    stateProvince = "NY",
                    countryRegion = "USA",
                    postalCode = "67890",
                    rowguid = "23456789-2345-2345-2345-234567890123",
                    modifiedDate = "2023-02-01 15:30:00"
                )
            ),
            snackbarHostState = snackbarHostState,
            onImportCsv = {}
        )
    }
}