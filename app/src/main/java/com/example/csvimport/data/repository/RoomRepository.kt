package com.example.csvimport.data.repository

import com.example.csvimport.data.db.AddressEntity
import kotlinx.coroutines.flow.Flow

interface RoomRepository {
    suspend fun getAddressById(id: Int): AddressEntity?

    fun observeAddresses(): Flow<List<AddressEntity>>

    suspend fun insertAddress(address: AddressEntity): Long

    suspend fun replaceAddresses(items: List<AddressEntity>)

    suspend fun updateAddressByCityStateAndModified(id: Int, city: String, state: String, modifiedDate: String): Int

    suspend fun updateAddress(address: AddressEntity): Int
}