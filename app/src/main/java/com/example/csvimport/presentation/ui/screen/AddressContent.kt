package com.example.csvimport.presentation.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.csvimport.R
import com.example.csvimport.data.db.AddressEntity
import com.example.csvimport.presentation.ui.theme.CsvImportTheme

private val Accent = Color(0xFFFF4438)
private val AccentContainer = Color(0xFFFFF1EF)
private val ScreenBackground = Color(0xFFF7F7F9)
private val Subtitle = Color(0xFF808080)

/**
 * TODO:
 * -sacar colores al tema
 * -
 * */
@Composable
fun AddressContent(
    snackbarHostState: SnackbarHostState,
    searchText: String,
    onSearchChange: (String) -> Unit,
    addresses: List<AddressEntity>,
    selectedAddress: AddressEntity? = null,
    onImportCsv: () -> Unit,
    onEditAddress: (AddressEntity) -> Unit = {},
    onDismissEditDialog: () -> Unit = {},
    onSaveAddress: AddressEntity.(String, String) -> Unit = { _, _ -> }
) {

    Scaffold(
        containerColor = ScreenBackground,
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onImportCsv,
                containerColor = Accent,
                contentColor = Color.White,
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.outline_drive_folder_upload_24),
                        contentDescription = null
                    )
                },
                text = {
                    Text("Import CSV")
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
        ) {

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Addresses",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "${addresses.size} imported",
                color = Subtitle
            )


            Spacer(Modifier.height(20.dp))

            OutlinedTextField(
                value = searchText,
                onValueChange = onSearchChange,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                placeholder = {
                    Text("Search address...")
                },
                leadingIcon = {
                    Icon(
                        painterResource(R.drawable.search_24),
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    AnimatedVisibility(searchText.isNotBlank()) {
                        IconButton(
                            onClick = { onSearchChange("") }
                        ) {
                            Icon(
                                painterResource(R.drawable.close_24),
                                contentDescription = null
                            )
                        }
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Accent,
                    focusedLeadingIconColor = Accent,
                    cursorColor = Accent
                )
            )

            Spacer(Modifier.height(20.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                items(
                    addresses,
                    key = { it.addressId }
                ) { address ->

                    AddressRow(
                        address = address,
                        onEdit = onEditAddress
                    )
                }
            }

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
fun AddressRow(
    modifier: Modifier = Modifier,
    address: AddressEntity,
    onEdit: (AddressEntity) -> Unit
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(AccentContainer),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        painter = painterResource(R.drawable.outline_location_on_24),
                        contentDescription = null,
                        tint = Accent
                    )
                }

                Spacer(Modifier.width(14.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = address.addressLine1,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    address.addressLine2?.let {

                        Spacer(Modifier.height(2.dp))

                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Subtitle
                        )
                    }

                    Spacer(Modifier.height(4.dp))

                    Text(
                        text = "ID ${address.addressId}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Subtitle
                    )
                }

                FilledTonalButton(
                    onClick = { onEdit(address) },
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = AccentContainer
                    )
                ) {

                    Text(
                        "Edit",
                        color = Accent
                    )
                }
            }

            Spacer(Modifier.height(18.dp))

            HorizontalDivider()

            Spacer(Modifier.height(18.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                AddressChip(address.city)

                AddressChip(address.stateProvince)

                AddressChip(address.postalCode)
            }

            Spacer(Modifier.height(18.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = address.countryRegion,
                    color = Subtitle,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = address.modifiedDate,
                    color = Subtitle,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
private fun AddressChip(
    text: String
) {

    Surface(
        shape = RoundedCornerShape(50),
        color = AccentContainer
    ) {

        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 6.dp
            ),
            color = Accent,
            style = MaterialTheme.typography.labelMedium
        )
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

@Preview(showBackground = true)
@Composable
private fun AddressContentPreview() {

    val snackbarHostState = remember { SnackbarHostState() }

    CsvImportTheme {
        AddressContent(
            snackbarHostState = snackbarHostState,
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
            searchText = "",
            onSearchChange = {},
            selectedAddress = null,
            onImportCsv = {},
            onEditAddress = {},
            onDismissEditDialog = {},
            onSaveAddress = { _, _ -> }
        )
    }
}