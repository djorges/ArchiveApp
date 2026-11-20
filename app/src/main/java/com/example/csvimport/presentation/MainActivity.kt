package com.example.csvimport.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.csvimport.presentation.nav.NavigationHost
import com.example.csvimport.presentation.ui.theme.CsvImportTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            CsvImportTheme {
                NavigationHost()
            }
        }
    }
}