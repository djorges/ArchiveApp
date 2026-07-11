package com.example.csvimport.presentation

import android.app.Application
import com.example.csvimport.data.di.AppModule
import com.example.csvimport.data.di.FirebaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.KoinApplication
import org.koin.plugin.module.dsl.startKoin

@KoinApplication(
    modules = [
        AppModule::class
    ]
)
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin<MainApplication> {
            androidLogger()
            androidContext(this@MainApplication)
        }
    }
}