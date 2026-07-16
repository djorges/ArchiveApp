package com.example.csvimport.data.repository

interface UserRepository {

    suspend fun createUser(
        uid: String,
        name: String
    ): Result<Unit>
}