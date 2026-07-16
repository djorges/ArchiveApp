package com.example.csvimport.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressDao {

    @Query("SELECT * FROM addresses ORDER BY addressId ASC")
    fun observeAll(): Flow<List<AddressEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(address: AddressEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(addresses: List<AddressEntity>) : List<Long>

    @Delete
    suspend fun delete(address: AddressEntity): Int

    @Query("DELETE FROM addresses")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM addresses WHERE addressId = :id")
    suspend fun getById(id: Int): AddressEntity?

    @Query("SELECT COUNT(*) FROM addresses")
    suspend fun count(): Int

    @Update
    suspend fun update(address: AddressEntity): Int

    @Query("UPDATE addresses SET city = :city, stateProvince = :state, modifiedDate = :modifiedDate WHERE addressId = :id")
    suspend fun updateCityStateAndModified(id: Int, city: String, state: String, modifiedDate: String): Int
}