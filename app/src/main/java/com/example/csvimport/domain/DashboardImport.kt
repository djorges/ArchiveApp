package com.example.csvimport.domain

data class DashboardImport(
    val fileName: String,
    val date: String,
    val processed: Int,
    val errors: Int,
    val fileSize: String,
    val status: ImportStatus
)