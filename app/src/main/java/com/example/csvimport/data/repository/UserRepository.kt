package com.example.csvimport.data.repository

interface UserRepository {

    suspend fun createUser(user: User): Result<Unit>

    suspend fun getUserById(uid: String): Result<User?>

    suspend fun getAllUsers(): Result<List<User>>

    suspend fun updateUser(user: User): Result<Unit>

    suspend fun deleteUser(uid: String): Result<Unit>

    suspend fun userExists(uid: String): Result<Boolean>
}
