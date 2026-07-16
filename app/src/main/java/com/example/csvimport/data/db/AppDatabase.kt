package com.example.csvimport.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AddressEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun addressDao(): AddressDao
}