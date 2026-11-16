package com.example.csvimport.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Singleton

@Singleton(binds = [UserRepository::class])
class UserRepositoryImpl(
    private val firestore: FirebaseFirestore
) : UserRepository {

    private companion object {
        const val USERS_COLLECTION = "Users"
    }

    private val usersCollection
        get() = firestore.collection(USERS_COLLECTION)

    // 1. Crear usuario
    override suspend fun createUser(user: User): Result<Unit> =
        safeCall {
            val data = mapOf(
                "name" to user.name,
                "role" to user.role
            )

            usersCollection
                .document(user.uid)
                .set(data)
                .await()
        }

    // 2. Obtener usuario por UID
    override suspend fun getUserById(uid: String): Result<User?> =
        safeCall {
            val document = usersCollection
                .document(uid)
                .get()
                .await()

            if (!document.exists()) {
                null
            } else {
                document.toObject(User::class.java)
                    ?.copy(uid = document.id)
            }
        }

    // 3. Obtener todos los usuarios
    override suspend fun getAllUsers(): Result<List<User>> =
        safeCall {
            usersCollection
                .get()
                .await()
                .documents
                .mapNotNull { document ->
                    document.toObject(User::class.java)
                        ?.copy(uid = document.id)
                }
        }

    // 4. Actualizar usuario
    override suspend fun updateUser(user: User): Result<Unit> =
        safeCall {
            val updates = mapOf(
                "name" to user.name,
                "role" to user.role
            )

            usersCollection
                .document(user.uid)
                .update(updates)
                .await()
        }

    // 5. Eliminar usuario
    override suspend fun deleteUser(uid: String): Result<Unit> =
        safeCall {
            usersCollection
                .document(uid)
                .delete()
                .await()
        }

    // 6. Comprobar existencia
    override suspend fun userExists(uid: String): Result<Boolean> =
        safeCall {
            usersCollection
                .document(uid)
                .get()
                .await()
                .exists()
        }

    // Manejo centralizado de errores
    private suspend fun <T> safeCall(
        action: suspend () -> T
    ): Result<T> {
        return try {
            Result.success(action())
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
