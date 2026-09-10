package com.example.csvimport.presentation.viewmodel

import android.content.ContentResolver
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csvimport.data.db.AddressEntity
import com.example.csvimport.data.repository.RoomRepository
import com.example.csvimport.data.util.AddressCsvParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class AddressViewModel(
    private val repository: RoomRepository,
    private val addressCsvParser: AddressCsvParser
): ViewModel() {
    val _uiMessage = MutableStateFlow<String>("")
    val uiMessage: StateFlow<String> = _uiMessage.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    val addresses: StateFlow<List<AddressEntity>> =
        combine(
            repository.observeAddresses(),
            _searchText
        ) { addresses, query ->

            if (query.isBlank()) {
                addresses
            } else {
                val search = query.trim().lowercase()

                addresses.filter { address ->
                    address.addressLine1.lowercase().contains(search) ||
                            address.addressLine2?.lowercase()?.contains(search) == true ||
                            address.city.lowercase().contains(search) ||
                            address.stateProvince.lowercase().contains(search) ||
                            address.countryRegion.lowercase().contains(search) ||
                            address.postalCode.lowercase().contains(search) ||
                            address.addressId.toString().contains(search)
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
    private val _selectedAddress = MutableStateFlow<AddressEntity?>(null)
    val selectedAddress = _selectedAddress.asStateFlow()

    private val _editAddress = MutableStateFlow<AddressEntity?>(null)
    val editAddress: StateFlow<AddressEntity?> = _editAddress.asStateFlow()

    private val _editCity = MutableStateFlow("")
    val editCity: StateFlow<String> = _editCity.asStateFlow()

    private val _editState = MutableStateFlow("")
    val editState: StateFlow<String> = _editState.asStateFlow()

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun onEditAddress(address: AddressEntity) {
        _selectedAddress.value = address
    }

    fun onDismissEditDialog() {
        _selectedAddress.value = null
    }

    fun saveCityState(id:Int, city: String, state: String) {

        _selectedAddress.value = null
    }

    fun importCsv(contentResolver: ContentResolver, uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val items = contentResolver.openInputStream(uri)?.use {
                    addressCsvParser.parse(it)
                } ?: emptyList()

                repository.replaceAddresses(items)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}