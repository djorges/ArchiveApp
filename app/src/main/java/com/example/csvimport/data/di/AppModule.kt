package com.example.csvimport.data.di

import android.content.Context
import androidx.room.Room
import com.example.csvimport.data.db.AddressDao
import com.example.csvimport.data.db.AppDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Singleton
import kotlin.jvm.java

@Module(includes = [FirebaseModule::class])
@ComponentScan("com.example.csvimport")
class AppModule {

    @Singleton
    fun provideDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()

    @Singleton
    fun provideAddressDao(database: AppDatabase): AddressDao = database.addressDao()
}