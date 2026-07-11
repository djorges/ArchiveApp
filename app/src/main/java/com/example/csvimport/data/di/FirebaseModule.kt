package com.example.csvimport.data.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.core.annotation.Module
import org.koin.core.annotation.Singleton

@Module
class FirebaseModule {
    @Singleton
    fun firebaseAuth() = FirebaseAuth.getInstance()

    @Singleton
    fun firestore() = FirebaseFirestore.getInstance()
}