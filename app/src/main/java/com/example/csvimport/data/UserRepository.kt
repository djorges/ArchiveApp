package com.example.csvimport.data

interface UserRepository {

    suspend fun createUser(
        uid: String,
        name: String
    ): Result<Unit>
}