package com.example.csvimport.data.repository

import com.example.csvimport.data.db.AddressDao
import com.example.csvimport.data.db.AddressEntity
import org.koin.core.annotation.Singleton

@Singleton(binds = [RoomRepository::class])
class RoomRepositoryImpl (
    private val addressDao: AddressDao
) : RoomRepository {
    override suspend fun getAddressById(id: Int) = addressDao.getById(id)

    override fun observeAddresses() = addressDao.observeAll()

    override suspend fun insertAddress(address: AddressEntity) = addressDao.insert(address)

    override suspend fun replaceAddresses(items: List<AddressEntity>){
        addressDao.deleteAll()
        addressDao.insertAll(items)
    }

    override suspend fun updateAddressByCityStateAndModified(id: Int, city: String, state: String, modifiedDate: String): Int{
        return addressDao.updateCityStateAndModified(id, city, state, modifiedDate)
    }

    override suspend fun updateAddress(address: AddressEntity): Int = addressDao.update(address)
}