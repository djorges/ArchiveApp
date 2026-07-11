package com.example.csvimport.data.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Singleton

@Module(includes = [FirebaseModule::class])
@ComponentScan("com.example.csvimport")
class AppModule {

}